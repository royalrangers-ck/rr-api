package com.royalrangers.controller.achievement;


import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.achievement.TestForAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestForAdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestForAdminService testForAdminService;

    @GetMapping("/achievements/userTest/submitted")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUserTestsForAdmin(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        return testForAdminService.getUsersData(user);
    }
}
