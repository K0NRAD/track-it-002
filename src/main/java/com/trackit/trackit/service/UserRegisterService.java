package com.trackit.trackit.service;

import com.trackit.trackit.model.User;
import com.trackit.trackit.model.UserPersonalData;
import com.trackit.trackit.repository.UserPersonalDataRepository;
import com.trackit.trackit.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    private final UserRepository userRepository;
    private final UserPersonalDataRepository userPersonalDataRepository;

    public UserRegisterService(UserRepository userRepository, UserPersonalDataRepository userPersonalDataRepository) {
        this.userRepository = userRepository;
        this.userPersonalDataRepository = userPersonalDataRepository;
    }

    public boolean registerNewUser(String username, String password, String personnelNumber, String firstName, String lastName) {
        User newUser = new User(username, password);
        System.out.println(newUser);
        UserPersonalData newUserPersonalData = new UserPersonalData(personnelNumber, newUser, firstName, lastName);
        System.out.println(newUserPersonalData);

        userRepository.save(newUser);
        userPersonalDataRepository.save(newUserPersonalData);

        return true;
        /*
        try{
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            System.out.println(newUser);
            UserPersonalData newUserPersonalData = new UserPersonalData(personnelNumber, newUser, firstName, lastName);
            System.out.println(newUserPersonalData);

            userRepository.save(newUser);
            userPersonalDataRepository.save(newUserPersonalData);

            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        */
    }
}
