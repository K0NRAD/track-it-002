package com.trackit.trackit.controller;


import com.trackit.trackit.model.Employee;
import com.trackit.trackit.service.EmployeeLoginService;
import com.trackit.trackit.service.EmployeeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final EmployeeLoginService employeeLoginService;

    @Autowired
    private final EmployeeRegisterService employeeRegisterService;

    @GetMapping(path = "api/employee/loginEmployee")
    public Employee getEmployeeId(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        return employeeLoginService.getEmployeeByUsernameAndPassword(username, password);
    }

    @GetMapping(path = "api/employee/registerEmployee")
    public boolean registerEmployee(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "personnelNumber", required = true) String personnelNumber,
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // api/employee/registerEmployee(username, password, personnelNumber, firstName, lastName)
        // returns true if the registration has been successful
        // returns false if the registration has failed
        return employeeRegisterService.registerNewEmployee(username, password, personnelNumber, firstName, lastName);
    }
}
