package com.trackit.trackit.service;

import com.trackit.trackit.model.Employee;
import com.trackit.trackit.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLoginService {
    private final EmployeeRepository employeeRepository;

    public EmployeeLoginService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getUserByUsernameAndPassword(String username, String password) {
        Employee employee = employeeRepository.getUserByUsernameAndPassword(username, password);

        return employee;
    }
}
