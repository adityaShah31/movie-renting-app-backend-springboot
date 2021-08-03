package com.fmovies.restapimongodb.controller;


import com.fmovies.restapimongodb.CustomResponse;
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

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addNewUser(@RequestBody User user) {

        User newUser = userService.createNewUser(user);

        if (newUser != null)
            return new ResponseEntity(new CustomResponse(newUser, "New user created fam!"), HttpStatus.OK);

        return new ResponseEntity(new CustomResponse(null, "Check your inputs fool! Could not validate them!"),
                HttpStatus.BAD_REQUEST);
    };



    @PostMapping(value = "/login", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity validateUser(@RequestBody User user) {

        boolean isUserAuthorized = userService.checkUserCredentials(user.getEmail(), user.getPassword());

        if (isUserAuthorized)
            return new ResponseEntity(new CustomResponse("Success", "You're logged in fam!"), HttpStatus.OK);

        return new ResponseEntity(new CustomResponse("Failed", "Check your credentials fool! Could not find them in " +
                "the Db"),
                HttpStatus.NOT_FOUND);

    }

}
