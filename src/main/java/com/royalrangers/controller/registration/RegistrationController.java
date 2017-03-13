package com.royalrangers.controller.registration;

import com.google.gson.Gson;
import com.royalrangers.bean.UserBean;
import com.royalrangers.model.User;
import com.royalrangers.service.EmailService;
import com.royalrangers.service.UserService;
import com.royalrangers.utils.ResponseBuilder;
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
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody String jsonUser) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonUser, UserBean.class);

        if (userService.isEmailExist(userBean.getEmail())) {
            return new ResponseEntity(ResponseBuilder.fail("User with this email already exists"), HttpStatus.CONFLICT);
        }

        User user = userService.createUserFromUserForm(userBean);
        String confirmLink = userService.getConfimRegistrationLink(user);
        emailService.sendEmail(user, "RegistrationConfirm", "EmailTemplate.ftl", confirmLink);

        return new ResponseEntity(ResponseBuilder.success("User created successfully"), HttpStatus.OK);
    }
}
