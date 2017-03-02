package com.royalrangers.registration.controller;

import com.google.gson.Gson;
import com.royalrangers.model.User;
import com.royalrangers.registration.service.UserService;
import com.royalrangers.registration.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private Validator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> registration(String jsonUser) {
        Gson gson = new Gson();
        String message;
        User user = gson.fromJson(jsonUser, User.class);
        if (userValidator.validate(user)) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userService.save(user);
            message = "User created successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = "User with this email already exists";
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
