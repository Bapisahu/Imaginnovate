package com.Imaginnovate.service;

import com.Imaginnovate.dto.TaxDeduction;
import com.Imaginnovate.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<TaxDeduction> calculateTaxDeductions();
}


