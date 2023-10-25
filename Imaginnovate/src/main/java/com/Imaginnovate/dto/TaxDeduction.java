package com.Imaginnovate.dto;

import lombok.Data;

@Data
public class TaxDeduction {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private Double taxAmount;
    private Double cessAmount;


}

