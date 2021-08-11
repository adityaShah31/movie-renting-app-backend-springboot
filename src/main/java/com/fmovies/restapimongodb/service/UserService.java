package com.fmovies.restapimongodb.service;

import com.fmovies.restapimongodb.model.User;
import com.fmovies.restapimongodb.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user) throws Exception {

        User exisitingUser = userRepository.findByEmail(user.getEmail());

        if (exisitingUser == null) {
            String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            return userRepository.insert(user);
        }

        throw new Exception("Email already exists FOOL, try something else!");

    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User existingUser = userRepository.findByEmail(userEmail);

        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(),
                existingUser.getPassword(), new ArrayList<>());

    }
}
