package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.TestRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.TestService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/achievements/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    public ResponseResult getAllTest() {
        try {
            return ResponseBuilder.success(testService.getAllTest());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Tests");
        }
    }

    @PostMapping
    public ResponseResult addTest(@RequestBody TestRequestDto params) {
        try {
            testService.addTest(params);
            return ResponseBuilder.success("Test saved successfully");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed adding Test");
        }
    }

    @GetMapping("/{testId}")
    public ResponseResult getTestById(@PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.getTestById(testId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Test by id");
        }
    }

    @DeleteMapping("/{testId}")
    public ResponseResult deleteTestById(@PathVariable Long testId) {
        try {
            testService.deleteTestById(testId);
            return ResponseBuilder.success("Test was successfully deleted");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete Test");
        }
    }

    @PutMapping("/{testId}")
    public ResponseResult editTestById(@RequestBody TestRequestDto params, @PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.editTest(params, testId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Task");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set Test logo")
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
    @ApiOperation(value = "Delete logo")
    public ResponseResult delete(@RequestParam("id") Long testId) {
        try {
            testService.deleteTestLogo(testId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

}