package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.user.UserRegistrationDto;
import com.royalrangers.exception.TokenException;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.service.UserService;
import com.royalrangers.service.TokenService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    @ApiOperation(value = "Add user to database")
    public ResponseResult registration(@RequestBody UserRegistrationDto userInfo) {
        try {
            userService.registerUser(userInfo);
        } catch (UserRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
        log.info("User " + userInfo.getEmail() + " is successfully created");
        return ResponseBuilder.success("User is successfully created");
    }

    @GetMapping("/confirm")
    @ApiOperation(value = "Confirm user email by given token")
    public ResponseResult registrationConfirm(@RequestParam("token") String token) {
        try {
            VerificationToken verificationToken = tokenService.getVerificationToken(token);
            userService.confirmUser(verificationToken);
        } catch (TokenException e) {
            log.info(e.getMessage());
            return ResponseBuilder.fail(e.getMessage());
        }
        log.info("Verification token " + token + " is confirmed");
        return ResponseBuilder.success("User confirm registration successfully");
    }

    @GetMapping("/check")
    @ApiOperation(value = "Check is user with such email already exists")
    public ResponseResult checkEmail(@RequestParam("email") String email) {

        log.info("Checking email " + email);

        if (userService.isEmailExist(email)) {
            return ResponseBuilder.fail("User with such an email already exists!");
        }

        return ResponseBuilder.success();
    }

    @PostMapping("/resend/confirmation")
    @ApiOperation(value = "Resend confirmation email")
    public ResponseResult resendConfirmationLink(@RequestParam("email") String email) {
        try {
            userService.resendConfirmation(email);
        } catch (UserRepositoryException ex) {
            return ResponseBuilder.fail(ex.getMessage());
        } catch (UnknownHostException e) {
            log.error("Error in confirmation URL for " + email);
            return ResponseBuilder.fail("Error in confirmation URL");
        }
        return ResponseBuilder.success("Confirmation email is successfully resending.");
    }
}
