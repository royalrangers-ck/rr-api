package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.YearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/achievements/year")
public class YearAchievementController {

    @Autowired
    private YearAchievementService yearAchievementService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    @ApiOperation(value = "Get list of all year achievement")
    public ResponseResult getAllYearAchievement() {
        try {
            return ResponseBuilder.success(yearAchievementService.getAllYearAchievement());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all yearAchievements");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add year achievement related to three-year achievement")
    public ResponseResult addYearAchievement(@RequestBody AchievementRequestDto params) {
        try {
            log.info("Add YearAchievement " + params.getName());
            return ResponseBuilder.success(yearAchievementService.addYearAchievement(params));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add yearAchievements");
        }
    }

    @GetMapping("/{yearId}")
    @ApiOperation(value = "Get year achievement info")
    public ResponseResult getYearAchievementById(@PathVariable Long yearId) {
        try {
            return ResponseBuilder.success(yearAchievementService.getYearAchievementById(yearId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get yearAchievements by id");
        }
    }

    @DeleteMapping("/{yearId}")
    @ApiOperation(value = "Delete year achievement")
    public ResponseResult deleteYearAchievement(@PathVariable Long yearId) {
        try {
            yearAchievementService.deleteYearAchievement(yearId);
            return ResponseBuilder.success("Successful delete yearAchievements");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete yearAchievements");
        }
    }

    @PutMapping("/{yearId}")
    @ApiOperation(value = "Update year achievement")
    public ResponseResult editYearAchievement(@RequestBody AchievementRequestDto params, @PathVariable Long yearId) {
        try {
            return ResponseBuilder.success(yearAchievementService.editYearAchievement(params, yearId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit yearAchievements");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set year achievement logo")
    public ResponseResult uploadLogo(@RequestParam("yearAchievementId") Long yearAchievementId, @RequestParam("file") MultipartFile file) {
        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.YEAR_ACHIEVEMENT_LOGO);
            yearAchievementService.setLogoUrl(logoUrl, yearAchievementId);
            return ResponseBuilder.success("LogoUrl", logoUrl);
        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @ApiOperation(value = "Delete year achievement logo")
    public ResponseResult delete(@RequestParam("yearAchievementId") Long yearAchievementId) {
        try {
            yearAchievementService.deleteLogo(yearAchievementId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}