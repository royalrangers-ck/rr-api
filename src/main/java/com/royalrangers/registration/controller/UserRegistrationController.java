package com.royalrangers.registration.controller;

import com.google.gson.Gson;
import com.royalrangers.model.User;
import com.royalrangers.registration.pojo.UserBean;
import com.royalrangers.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> registration(String jsonUser) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonUser, UserBean.class);

        if (!userService.validate(userBean))
            return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);

        User user = userService.convertPojoToEntity(userBean);
        userService.SaveUser(userBean, user);

        return new ResponseEntity<>("User created successfully", HttpStatus.OK);
    }
}
