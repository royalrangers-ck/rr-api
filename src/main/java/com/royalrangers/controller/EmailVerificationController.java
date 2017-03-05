package com.royalrangers.controller;

import com.royalrangers.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailVerificationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/open/checkEmail")
    public ResultResponse checkEmail(@RequestBody Email email) {
        Boolean responseStatus = false;
        String responseMessage;
        String mail = email.getEmail();

        log.info("checking email: " + mail);

        if (mail != null) {
            try {
                int count = userRepository.countByEmail(mail);
                if (count > 0) {
                    responseMessage = "user with this email already exist";
                } else {
                    responseMessage = "user with this email not exist";
                }
                responseStatus = true;
            } catch (Exception e) {
                responseMessage = e.getMessage();
            }
        } else {
            responseMessage = "invalid request";
        }
        return new ResultResponse(responseStatus, responseMessage);
    }
}
