package com.royalrangers.registration.controller;

import com.royalrangers.bean.Email;
import com.royalrangers.bean.ResultResponse;
import com.royalrangers.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegistrationVerificationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration/check/email")
    public ResultResponse checkEmail(@RequestBody Email email) {

        Boolean responseStatus = false;
        String responseMessage;
        String mail = email.getEmail();

        log.info("Checking email: " + mail);

        if (mail != null) {
            try {
                int count = userRepository.countByEmail(mail);
                if (count > 0) {
                    responseMessage = "User with this email already exists.";
                } else {
                    responseMessage = "User with this email not exists.";
                }
                responseStatus = true;
            } catch (Exception e) {
                responseMessage = e.getMessage();
            }
        } else {
            responseMessage = "Invalid request";
        }
        return new ResultResponse(responseStatus, responseMessage);
    }

    @PostMapping("/registration/check/username")
    public ResultResponse checkUsername(@RequestBody Email email) {
        return null;
    }
}
