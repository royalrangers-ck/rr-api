package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserTestRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserTestService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements/userTest")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    public ResponseResult getAllUserTest() {
        try {
            return ResponseBuilder.success(userTestService.findAllForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserTestAchievement");
        }
    }

    @JsonView(Views.AchievementUser.class)
    @GetMapping("/submitted")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUserTestsForAdmin() {
        return ResponseBuilder.success(userTestService.getSubmittedUsersTestsbyPlatoon());
    }

    @PostMapping
    public ResponseResult addUserTest(@RequestBody UserTestRequestDto params) {
        try {
            userTestService.addUserTest(params);
            return ResponseBuilder.success("Successfully added UserTestAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserTestAchievement");
        }
    }

    @JsonView(Views.Achievement.class)
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
    public ResponseResult editUserTest(@RequestBody UserAchievementRequestDto params, @PathVariable Long userTestId) {
        try {
            userTestService.editUserTest(params, userTestId);
            return ResponseBuilder.success("Successfully editing UserTest");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit UserTest");
        }
    }
}
