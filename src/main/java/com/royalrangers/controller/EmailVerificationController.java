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
        log.info("checking email: " +email.getEmail());
        if (userRepository.countByEmail(email.getEmail()) > 0) {
            return new ResultResponse("exist");
        }
        return new ResultResponse("not exist");
    }
}
