package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam Map <String,Object> params) {
        String email = (String) params.get("email");
        User user = userRepository.findByEmail(email);
        return loginService.loginInformation(user);
    }
}
