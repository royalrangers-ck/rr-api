package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.TestAgeGroupDto;
import com.royalrangers.dto.achievement.TestRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.Views;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.TestService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/achievements/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    @ApiOperation(value = "Get list of all tests")
    @JsonView(Views.Achievement.class)
    public ResponseResult getAllTest() {
        try {
            return ResponseBuilder.success(testService.getAllTest());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get Tests");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add test")
    public ResponseResult addTest(@RequestBody TestRequestDto params) {
        try {
            log.info("Add Test " + params.getName());
            return ResponseBuilder.success(testService.addTest(params));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed adding Test");
        }
    }

    @GetMapping("/{testId}")
    @ApiOperation(value = "Get test info")
    public ResponseResult getTestById(@PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.getTestById(testId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get Test by id");
        }
    }


    @GetMapping("/group/{userAgeGroup}")
    @ApiOperation(value = "Get list of all tests for age groups")
    public ResponseResult getTestsByUserAgeGroup(@PathVariable String userAgeGroup) {
        try {
            return ResponseBuilder.success(testService.getAllTestByUserAgeGroup(UserAgeGroup.valueOf(userAgeGroup.toUpperCase())));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all Test by UserAgeGroup");
        }
    }

    @DeleteMapping("/{testId}")
    @ApiOperation(value = "Delete test")
    public ResponseResult deleteTestById(@PathVariable Long testId) {
        try {
            testService.deleteTestById(testId);
            return ResponseBuilder.success("Test was successfully deleted");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete Test");
        }
    }

    @PutMapping("/{testId}")
    @ApiOperation(value = "Update test")
    public ResponseResult editTestById(@RequestBody TestRequestDto params, @PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.editTest(params, testId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit Task");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set test logo")
    public ResponseResult uploadTestLogo(@RequestParam("id") Long testId, @RequestParam("file") MultipartFile file) {
        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.TEST_LOGO);
            testService.setLogoUrl(logoUrl, testId);
            return ResponseBuilder.success("LogoUrl", logoUrl);
        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @ApiOperation(value = "Delete test logo")
    public ResponseResult delete(@RequestParam("id") Long testId) {
        try {
            testService.deleteTestLogo(testId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

}