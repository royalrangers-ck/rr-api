package com.royalrangers.controller;

import com.royalrangers.model.User;
import com.royalrangers.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailVerificationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/open/checkEmail")
    public ResultResponse checkEmail(@RequestBody Email email) {
        System.err.println(email.getEmail());
        User user = userRepository.findByEmail(email.getEmail());
        if (user == null) {
            return new ResultResponse("not exist");
        }
        return new ResultResponse("exist");
    }
}
