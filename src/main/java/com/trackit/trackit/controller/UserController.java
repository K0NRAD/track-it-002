package com.trackit.trackit.controller;


import com.trackit.trackit.model.User;
import com.trackit.trackit.service.UserLoginService;
import com.trackit.trackit.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserLoginService userLoginService;

    @Autowired
    private final UserRegisterService userRegisterService;

    @GetMapping(path = "api/user/getUserId")
    public Long getUserId(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ){
        /*
        api/user/getUserId(username, password)

        username & password wrong -- > Null
        username & password right -- > user_id
        */
        User returnUser = userLoginService.getUserByUsernameAndPassword(username, password);

        if(returnUser != null){
            return returnUser.getUserId();
        }else{
            return null;
        }
    }

    @GetMapping(path = "api/user/registerUser")
    public boolean registerUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "personnelNumber", required = true) String personnelNumber,
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName
    ){
        // api/user/registerUser(username, password, personnelNumber, firstName, lastName)
        // returns true if the registration has been successful
        // returns false if the registration has failed
        // http://localhost:8080/api/user/registerUser?username=%27testUsername%27&password=%27testPassword%27&personnelNumber=%27123456789%27&firstName=%27testFirstName%27&lastName=%27testLastName%27

        System.out.println(username);
        System.out.println(password);
        System.out.println(personnelNumber);
        System.out.println(firstName);
        System.out.println(lastName);

        boolean registrationStatus = userRegisterService.registerNewUser(username, password, personnelNumber, firstName, lastName);

        return registrationStatus;
    }
}
