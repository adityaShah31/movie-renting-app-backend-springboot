package com.fmovies.restapimongodb.service;

import com.fmovies.restapimongodb.model.LoginDto;
import com.fmovies.restapimongodb.model.User;
import com.fmovies.restapimongodb.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user) {

        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.insert(user);
    }


    public boolean checkUserCredentials(LoginDto user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return new BCryptPasswordEncoder().matches(user.getPassword(), existingUser.getPassword());
        }

        return false;
    }
}
