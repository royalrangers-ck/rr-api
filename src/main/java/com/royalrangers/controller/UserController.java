package com.royalrangers.controller;

import com.google.gson.Gson;
import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.UserBean;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.service.UserProfileService;
import com.royalrangers.service.UserService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody ResponseResult getAuthenticatedUserDetail() {

        String username = userService.getAuthenticatedUserEmail();
        log.info("get details for user " + username);

        return ResponseBuilder.success(profileService.getUserDetailByEmail(username));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseResult getUserDetailById(@PathVariable("id") Long id) {

        try {
            log.info("Get details for user id " + id);
            return ResponseBuilder.success(profileService.getUserDetailById(id));

        } catch (UserRepositoryException e){

            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @GetMapping("/approve/{id}")
    public ResponseResult getUserToApprove(@PathVariable("id") Long platoonId){

        Gson gson  = new Gson();
        String jsonList = gson.toJson(userService.getUsersForApprove(platoonId));

        return ResponseBuilder.success(jsonList);
    }

    @PostMapping("/approve")
    public ResponseResult approveUser(@RequestBody List<Long> ids) {

        userService.approveUsers(ids);

        return ResponseBuilder.success("Users approved successfully.");
    }

    @PostMapping("/reject")
    public ResponseResult rejectUser(@RequestBody List<Long> ids) {

        userService.rejectUsers(ids);

        return ResponseBuilder.success("Users disabled.");
    }
    @PutMapping(value = "/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseResult updateAuthorizedUser(@RequestBody UserBean update) {

        String email = userService.getAuthenticatedUserEmail();

        userService.updateUserByEmail(email, update);
        log.info("Update user " + email);

        return ResponseBuilder.success(String.format("User %s successful updated", email));
    }

    @PutMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateUserById(@PathVariable("id") Long id, @RequestBody UserBean userUpdate) {

        try {
            userService.updateUserById(id, userUpdate);
            log.info("Update user with id %d " + id);

            return ResponseBuilder.success(String.format("User with id %d successful updated", id));

        } catch (UserRepositoryException e){

            return ResponseBuilder.fail(e.getMessage());
        }
    }

}
