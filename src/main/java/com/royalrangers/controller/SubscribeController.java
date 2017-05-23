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
    @ApiOperation(value = "Add email to subscribers list")
    public ResponseResult subscribe(@RequestBody EmailDto request) {

        String email = request.getMail();
        log.info("Add subscriber: " + email);
        try {
            subscribeService.add(email);
            return ResponseBuilder.success("Email " + email + " is added to subscribers list");
        } catch (Exception e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping
    @ApiOperation(value = "Remove email from subscribers list")
    public ResponseResult unsubscribe(@RequestBody EmailDto request) {

        String email = request.getMail();
        log.info("Remove subscriber: " + email);
        try {
            subscribeService.remove(email);
            return ResponseBuilder.success("Email " + email + " is removed from subscribers list");
        } catch (Exception e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
