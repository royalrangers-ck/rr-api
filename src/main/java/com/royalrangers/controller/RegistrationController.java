package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.user.UserRegistrationDto;
import com.royalrangers.model.User;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.EmailService;
import com.royalrangers.service.UserService;
import com.royalrangers.service.VerificationTokenService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.Calendar;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ApiOperation(value = "Add user to database")
    public ResponseResult registration(@RequestBody UserRegistrationDto userInfo) {

        if (userService.isEmailExist(userInfo.getEmail())) {
            log.info(String.format("User with email '%s' already exists", userInfo.getEmail()));
            return ResponseBuilder.fail("User with this email already exists");
        }

        User user = userService.createUserFromUserForm(userInfo);

        try {
            String confirmLink = userService.getConfirmRegistrationLink(user);
            emailService.sendEmail(user,"RegistrationConfirm", "submit.email.inline.html", confirmLink);
        } catch (UnknownHostException e){
            log.error(String.format("Error in confirmation URL for '%s'"), userInfo.getEmail());
        }

        log.info(String.format("User '%s' is successfully created", userInfo.getEmail()));
        return ResponseBuilder.success("User is successfully created");
    }

    @GetMapping("/confirm")
    @ApiOperation(value = "Confirm user email")
    public ResponseResult registrationConfirm(@RequestParam("token") String token) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info(String.format("Verification token '%s' is invalid", token));
            return ResponseBuilder.fail("Verification token is invalid");
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            log.info(String.format("Verification token '%s' is expired", token));
            return ResponseBuilder.fail("Verification token is expired");
        }

        User user = verificationToken.getUser();
        user.setConfirmed(true);
        userRepository.save(user);

        log.info(String.format("Verification token '%s' is confirmed", token));
        return ResponseBuilder.success("User confirm registration successfully");
    }

    @GetMapping("/check")
    @ApiOperation(value = "Check is user with such email already exists")
    public ResponseResult checkEmail(@RequestParam("email") String email) {

        log.info(String.format("Checking email '%s'", email));

        if (userService.isEmailExist(email)) {
            return ResponseBuilder.fail("User with such an email already exists!");
        }

        return ResponseBuilder.success();
    }
}
