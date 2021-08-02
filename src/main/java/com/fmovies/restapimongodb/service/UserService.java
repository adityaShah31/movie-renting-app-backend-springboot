package com.fmovies.restapimongodb.service;

import com.fmovies.restapimongodb.model.User;
import com.fmovies.restapimongodb.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user) {

        //Validate input before inserting into Db

        return userRepository.insert(user);
    }


    public boolean isUserAuthorized(String userId) {


        return true;
    }
}
