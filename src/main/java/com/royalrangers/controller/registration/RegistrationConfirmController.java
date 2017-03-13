package com.royalrangers.controller.registration;

import com.royalrangers.model.User;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.repository.VerificationTokenRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class RegistrationConfirmController {
    @Autowired
    private UserService userService;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public ResponseEntity<String> registrationConfirm(@RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return new ResponseEntity<>("Verification token invalid", HttpStatus.CONFLICT);
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>("Verification token expired", HttpStatus.CONFLICT);
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveUser(user);

        return new ResponseEntity<>("User confirm registration successfully", HttpStatus.OK);
    }
}
