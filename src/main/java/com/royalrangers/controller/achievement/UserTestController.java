package com.royalrangers.controller.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.achievement.UserTestService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/userTest")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @GetMapping
    public ResponseResult getAllUserTest() {
        try {
            return ResponseBuilder.success(userTestService.findAllForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserTestAchievement");
        }
    }

    @PostMapping
    public ResponseResult addUserTest(@RequestBody Map<String, Object> params) {
        try {
            userTestService.addUserTest(params);
            return ResponseBuilder.success("Successfully added UserTestAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserTestAchievement");
        }
    }

    @GetMapping("/{userTestId}")
    public ResponseResult getUserTestById(@PathVariable Long userTestId) {
        try {
            return ResponseBuilder.success(userTestService.getUserTestById(userTestId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get UserTest by id");
        }
    }

    @DeleteMapping("/{userTestId}")
    public ResponseResult deleteUserTest(@PathVariable Long userTestId) {
        try {
            userTestService.deleteUserTest(userTestId);
            return ResponseBuilder.success("UserTest was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete UserTest");
        }
    }

    @PutMapping("/{userTestId}")
    public ResponseResult editUserTest(@RequestBody Map<String, Object> params, @PathVariable Long userTestId) {
        try {
            userTestService.editUserTest(params, userTestId);
            return ResponseBuilder.success("Successfully editing UserTest");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit UserTest");
        }
    }
}
