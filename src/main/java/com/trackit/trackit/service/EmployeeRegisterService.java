package com.trackit.trackit.service;

import com.trackit.trackit.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRegisterService {
    private final EmployeeRepository employeeRepository;

    public EmployeeRegisterService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean registerNewUser(String username, String password, String personnelNumber, String firstName, String lastName) {
        // not implemented yet
        return true;
    }
}
