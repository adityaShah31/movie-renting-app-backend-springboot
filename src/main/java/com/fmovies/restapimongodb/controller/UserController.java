package com.fmovies.restapimongodb.controller;


import com.fmovies.restapimongodb.CustomResponse;
import com.fmovies.restapimongodb.model.LoginDto;
import com.fmovies.restapimongodb.model.User;
import com.fmovies.restapimongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://f-movies.herokuapp.com")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addNewUser(@RequestBody @Valid User user) {

        try {
            User newUser = userService.createNewUser(user);

            return new ResponseEntity(new CustomResponse(newUser, "New user created fam!"), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(new CustomResponse(null, ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping(value = "/login", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity authenticateUser(@RequestBody @Valid LoginDto user) {


        try {
            Authentication authResponse = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword()));

            return new ResponseEntity(new CustomResponse("Success", "You're logged in fam!"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new CustomResponse("Failed", "Check your credentials fool!"),
                    HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable String id) {

        Optional<User> user = userService.getUser(id);

        if (user == null)
            return new ResponseEntity(new CustomResponse(null, "No such user exists!"), HttpStatus.NOT_FOUND);

        return new ResponseEntity(new CustomResponse(user, "Here's the user!"), HttpStatus.OK);

    }

}
