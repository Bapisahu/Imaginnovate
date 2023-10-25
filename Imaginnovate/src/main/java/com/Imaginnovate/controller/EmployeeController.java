package com.Imaginnovate.controller;

import com.Imaginnovate.dto.TaxDeduction;
import com.Imaginnovate.entity.Employee;
import com.Imaginnovate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
//    http://localhost:8080/employees
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }
//    http://localhost:8080/employees/tax-deductions
    @GetMapping("/tax-deductions")
    public ResponseEntity<List<TaxDeduction>> calculateTaxDeductions() {
        List<TaxDeduction> deductions = employeeService.calculateTaxDeductions();
        return ResponseEntity.ok(deductions);
    }

}


