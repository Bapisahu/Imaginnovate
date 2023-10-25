package com.Imaginnovate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @ElementCollection
    private List<@NotBlank @Pattern(regexp = "\\d{10}") String> phoneNumbers;

    @NotBlank
    private String dateOfJoining;

    @NotNull
    @Min(0)
    private Double salary;

}

