package com.trackit.trackit.controller;


import com.trackit.trackit.model.Employee;
import com.trackit.trackit.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @CrossOrigin
    @GetMapping(path = "api/employee/loginEmployee")
    public Employee loginEmployee(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        return employeeService.getEmployeeByUsernameAndPassword(username, password);
    }

    @CrossOrigin
    @GetMapping(path = "api/employee/registerEmployee")
    public boolean registerEmployee(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "personnelNumber", required = true) String personnelNumber,
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName
    ) {
        // api/employee/registerEmployee(username, password, personnelNumber, firstName, lastName)
        // returns true if the registration has been successful
        // returns false if the registration has failed
        return employeeService.registerNewEmployee(username, password, personnelNumber, firstName, lastName);
    }

    @CrossOrigin
    @GetMapping(path = "api/employee/getEmployeeDataByEmployeeId")
    public Employee getEmployeeData(
            @RequestParam(value = "employee_id", required = true) Long employeeId
    ){
        return employeeService.getEmployeeDataByEmployeeId(employeeId);
    }
}
