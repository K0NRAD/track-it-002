package com.trackit.trackit.service;

import com.trackit.trackit.model.Employee;
import com.trackit.trackit.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDataService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDataService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeDataByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }
}
