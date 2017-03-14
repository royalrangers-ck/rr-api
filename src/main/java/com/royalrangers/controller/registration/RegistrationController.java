package com.royalrangers.controller.registration;

import com.google.gson.Gson;
import com.royalrangers.bean.UserBean;
import com.royalrangers.model.User;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.repository.VerificationTokenRepository;
import com.royalrangers.service.EmailService;
import com.royalrangers.service.UserService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    VerificationTokenRepository tokenRepository;

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public ResponseEntity registrationConfirm(@RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return new ResponseEntity(ResponseBuilder.fail("Verification token invalid"), HttpStatus.CONFLICT);
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity(ResponseBuilder.fail("Verification token expired"), HttpStatus.CONFLICT);
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveUser(user);

        return new ResponseEntity(ResponseBuilder.success("User confirm registration successfully"), HttpStatus.OK);
    }

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
