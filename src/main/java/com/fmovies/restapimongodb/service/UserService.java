package com.fmovies.restapimongodb.service;

import com.fmovies.restapimongodb.model.LoginDto;
import com.fmovies.restapimongodb.model.User;
import com.fmovies.restapimongodb.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user) {
        return userRepository.insert(user);
    }


    public boolean checkUserCredentials(LoginDto user) {

        User validUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (validUser != null)
            return true;

        return false;
    }
}
