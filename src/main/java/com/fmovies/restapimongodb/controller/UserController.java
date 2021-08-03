package com.fmovies.restapimongodb.controller;


import com.fmovies.restapimongodb.CustomResponse;
import com.fmovies.restapimongodb.model.LoginDto;
import com.fmovies.restapimongodb.model.User;
import com.fmovies.restapimongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addNewUser(@RequestBody @Valid User user) {

        try{
            User newUser = userService.createNewUser(user);

            return new ResponseEntity(new CustomResponse(newUser, "New user created fam!"), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(new CustomResponse(null, ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    };



    @PostMapping(value = "/login", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity authenticateUser(@RequestBody @Valid LoginDto user) {

        boolean isUserAuthenticated = userService.checkUserCredentials(user);

        if (isUserAuthenticated)
            return new ResponseEntity(new CustomResponse("Success", "You're logged in fam!"), HttpStatus.OK);

        return new ResponseEntity(new CustomResponse("Failed", "Check your credentials fool! Could not find them in " +
                "the Db"),
                HttpStatus.NOT_FOUND);

    }

}
