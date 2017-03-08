package com.royalrangers.controller.registration;

import com.google.gson.Gson;
import com.royalrangers.bean.ResultResponse;
import com.royalrangers.model.User;
import com.royalrangers.bean.UserBean;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody String jsonUser) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonUser, UserBean.class);

        if (userService.isEmailExist(userBean.getEmail())) {
            return new ResponseEntity(new ResultResponse(false, "User with this email already exists"), HttpStatus.CONFLICT);
        }

        User user = userService.createUserFromUserForm(userBean);
        userService.saveUser(userBean, user);

        return new ResponseEntity(new ResultResponse(true, "User created successfully"), HttpStatus.OK);
    }
}
