package com.royalrangers.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.royalrangers.bean.ResponseResult;
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

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody ResponseResult getAuthorizedUserDetail(Principal principal) {

        String username = principal.getName();
        log.info("get details for user " + username);

        return ResponseBuilder.success(profileService.getUserDetailByEmail(username));
    }

    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseResult getUserDetailById(@PathVariable("id") Long id) {

        try {
            log.info("get details for user id " + id);
            return ResponseBuilder.success(profileService.getUserDetailById(id));

        } catch (UserRepositoryException e){

            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/user/approve/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserToApprove(@PathVariable("id") Long platoonId){

        Gson gson  = new Gson();
        String jsonList = gson.toJson(userService.getUsersForApprove(platoonId));

        return new ResponseEntity(ResponseBuilder.success(jsonList), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/approve/", method = RequestMethod.POST)
    public ResponseEntity approveUser(@RequestBody String approvedUserIdList) {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Long>>(){}.getType();
        ArrayList<Long> listId = gson.fromJson(approvedUserIdList, type);
        userService.setApproveToUser(listId);
        return new ResponseEntity(ResponseBuilder.success("Users approved successfuly."), HttpStatus.OK);
    }

}
