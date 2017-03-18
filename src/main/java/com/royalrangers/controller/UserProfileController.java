package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserProfileService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @GetMapping(value = "user")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody ResponseResult getAuthorizedUserDetail(Principal principal) {

        String username = principal.getName();
        log.info("get details for user " + username);

        return ResponseBuilder.success(profileService.getUserDetailByEmail(username));
    }

    @GetMapping(value = "user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseResult getUserDetailById(@PathVariable("id") Long id) {

        log.info("get details for user id " + id);

        return ResponseBuilder.success(profileService.getUserDetailById(id));
    }


}
