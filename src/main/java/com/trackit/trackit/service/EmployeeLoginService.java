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

    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        username = HashingStaticService.hashString(username);
        password = HashingStaticService.hashString(password);

        return employeeRepository.getUserByUsernameAndPassword(username, password);
    }
}
