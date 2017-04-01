package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public ResponseResult admin(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        return adminService.getUsersData(user);
    }
}
