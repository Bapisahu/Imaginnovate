package com.Imaginnovate.service.impl;

import com.Imaginnovate.dto.TaxDeduction;
import com.Imaginnovate.entity.Employee;
import com.Imaginnovate.repository.EmployeeRepository;
import com.Imaginnovate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


        @Override
        public List<TaxDeduction> calculateTaxDeductions() {
            List<TaxDeduction> deductions = new ArrayList<>();

            Iterable<Employee> employees = employeeRepository.findAll();

            for (Employee employee : employees) {
                double yearlySalary = calculateYearlySalary(employee);
                double taxAmount = calculateTaxAmount(yearlySalary);
                double cessAmount = calculateCessAmount(yearlySalary);

                TaxDeduction deduction = new TaxDeduction();
                deduction.setEmployeeId(employee.getEmployeeId());
                deduction.setFirstName(employee.getFirstName());
                deduction.setLastName(employee.getLastName());
                deduction.setYearlySalary(yearlySalary);
                deduction.setTaxAmount(taxAmount);
                deduction.setCessAmount(cessAmount);

                deductions.add(deduction);
            }

            return deductions;
        }

        private double calculateYearlySalary(Employee employee) {
            // Split the DOJ into year and month
            int dojYear = Integer.parseInt(employee.getDateOfJoining().substring(0, 4));
            int dojMonth = Integer.parseInt(employee.getDateOfJoining().substring(5, 7));

            // Get the current year and month
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

            // Calculate the number of months in the current financial year
            int monthsInCurrentFinancialYear = 12 - dojMonth + 1;
            if (currentMonth <= 3) {
                monthsInCurrentFinancialYear += currentMonth;
            }

            // Calculate the total salary for the financial year
            double totalSalary = employee.getSalary() * monthsInCurrentFinancialYear;

            // Calculate the loss of pay for days beyond the financial year
            int daysBeyondCurrentFinancialYear = 0;
            if (currentMonth > 3) {
                daysBeyondCurrentFinancialYear = (currentMonth - 3) * 30;
            }

            double lossOfPay = (employee.getSalary() / 30) * daysBeyondCurrentFinancialYear;

            return totalSalary - lossOfPay;
        }

        private double calculateTaxAmount(double yearlySalary) {
            double taxAmount = 0.0;

            if (yearlySalary <= 250000) {
                taxAmount = 0.0;
            } else if (yearlySalary <= 500000) {
                taxAmount = (yearlySalary - 250000) * 0.05;
            } else if (yearlySalary <= 1000000) {
                taxAmount = (250000 * 0.05) + (yearlySalary - 500000) * 0.10;
            } else {
                taxAmount = (250000 * 0.05) + (500000 * 0.10) + (yearlySalary - 1000000) * 0.20;
            }

            return taxAmount;
        }

        private double calculateCessAmount(double yearlySalary) {
            double cessAmount = 0.0;

            if (yearlySalary > 2500000) {
                double amountAboveThreshold = yearlySalary - 2500000;
                cessAmount = amountAboveThreshold * 0.02;
            }

            return cessAmount;
        }
    }



