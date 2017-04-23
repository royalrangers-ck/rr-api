package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.user.EmailDto;
import com.royalrangers.service.SubscribeService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @PostMapping
    @ApiOperation(value = "Create subscriber")
    public ResponseResult subscribe(@RequestBody EmailDto request) {

        String email = request.getMail();
        log.info("Add subscriber: " + email);
        try {
            subscribeService.add(email);
            return ResponseBuilder.success(String.format("Email %s added to subscribers list", email));
        } catch (Exception e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping
    @ApiOperation(value = "Delete subscriber")
    public ResponseResult unsubscribe(@RequestBody EmailDto request) {

        String email = request.getMail();
        log.info("Remove subscriber: " + email);
        try {
            subscribeService.remove(email);
            return ResponseBuilder.success(String.format("Email %s removed from subscribers list", email));
        } catch (Exception e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
