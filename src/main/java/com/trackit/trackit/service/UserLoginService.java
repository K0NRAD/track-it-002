package com.trackit.trackit.service;

import com.trackit.trackit.model.User;
import com.trackit.trackit.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);

        return user;
    }
}
