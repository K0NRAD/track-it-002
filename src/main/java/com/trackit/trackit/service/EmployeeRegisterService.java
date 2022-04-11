package com.trackit.trackit.service;

import com.trackit.trackit.model.Employee;
import com.trackit.trackit.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

@Service
public class EmployeeRegisterService {
    private final EmployeeRepository employeeRepository;

    public EmployeeRegisterService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean registerNewEmployee(String username, String password, String personnelNumber, String firstName, String lastName) {
        // Hash username & password
        username = HashingStaticService.hashString(username);
        password = HashingStaticService.hashString(password);

        // Save employee
        Employee employee = new Employee(
                username, password, personnelNumber, firstName, lastName
        );

        try {
            // Save the employee - return true
            employeeRepository.save(employee);

            return true;
        } catch (Exception e) {
            // Employee registration failed - return false & log employee registration data
            System.out.println("Error when trying to save the employee");
            System.out.println("Employee save data:");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println("personnelNumber: " + personnelNumber);
            System.out.println("firstName: " + firstName);
            System.out.println("lastName: " + lastName);

            return false;
        }
    }
}
