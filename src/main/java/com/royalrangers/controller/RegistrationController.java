package com.royalrangers.controller;

import com.google.gson.Gson;
import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.UserBean;
import com.royalrangers.model.User;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.EmailService;
import com.royalrangers.service.UserService;
import com.royalrangers.service.VerificationTokenService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Map;

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
    public ResponseResult registration(@RequestBody String jsonUser) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonUser, UserBean.class);

        if (userService.isEmailExist(userBean.getEmail())) {
            log.info(String.format("User with email '%s' already exists", userBean.getEmail()));
            return ResponseBuilder.fail("User with this email already exists");
        }

        User user = userService.createUserFromUserForm(userBean);
        String confirmLink = userService.getConfirmRegistrationLink(user);
        emailService.sendEmail(user, "RegistrationConfirm", "submit.email.inline.html", confirmLink);

        log.info(String.format("User '%s' is successfully created", userBean.getEmail()));
        return ResponseBuilder.success("User is successfully created");
    }

    @GetMapping("/confirm")
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

    @PostMapping("/check/email")
    public ResponseResult checkEmail(@RequestBody Map<String, Object> params) {

        String email = (String) params.get("email");
        log.info(String.format("Checking email '%s'", email));

        if (userService.isEmailExist(email)) {
            return ResponseBuilder.fail("User with such an email already exists!");
        }

        return ResponseBuilder.success();
    }
}
