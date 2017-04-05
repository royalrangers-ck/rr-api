package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.service.SubscribeService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;

    @PostMapping
    @ApiOperation(value = "Create subscriber")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "json", value = "Subscriber's email. Example: {\"email\": \"test@test.com\"}", paramType = "body", required = true)
    })
    public ResponseResult subscribe(@ApiIgnore @RequestBody Map<String, Object> params) {

        String email = (String) params.get("email");
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "json", value = "Subscriber's email. Example: {\"email\": \"test@test.com\"}", paramType = "body", required = true)
    })
    public ResponseResult unsubscribe(@ApiIgnore @RequestBody  Map<String, Object> params) {

        String email = (String) params.get("email");
        log.info("Remove subscriber: " + email);
        try {
            subscribeService.remove(email);
            return ResponseBuilder.success(String.format("Email %s removed from subscribers list", email));
        } catch (Exception e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
