package com.trackit.trackit.controller;


import com.trackit.trackit.model.Employee;
import com.trackit.trackit.service.EmployeeLoginService;
import com.trackit.trackit.service.EmployeeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final EmployeeLoginService employeeLoginService;

    @Autowired
    private final EmployeeRegisterService employeeRegisterService;

    @GetMapping(path = "api/employee/getEmployeeId")
    public Long getEmployeeId(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        /*
        api/employee/getEmployeeId(username, password)

        username & password wrong -- > Null
        username & password right -- > user_id
        */
        Employee employee = employeeLoginService.getUserByUsernameAndPassword(username, password);

        if (employee != null) {
            return employee.getEmployeeId();
        } else {
            return null;
        }
    }

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
        // http://localhost:8080/api/employee/registerEmployee?username=%27testUsername%27&password=%27testPassword%27&personnelNumber=%27123456789%27&firstName=%27testFirstName%27&lastName=%27testLastName%27

        boolean registrationStatus = employeeRegisterService.registerNewUser(username, password, personnelNumber, firstName, lastName);
        return registrationStatus;
    }
}
