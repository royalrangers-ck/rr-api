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
    public ResponseResult getAllYearAchievement() {
        try {
            return ResponseBuilder.success(yearAchievementService.getAllYearAchievement());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all yearAchievements");
        }
    }

    @PostMapping
    public ResponseResult addYearAchievement(@RequestBody AchievementRequestDto params) {
        try {
            yearAchievementService.addYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a yearAchievements");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add yearAchievements");
        }
    }

    @GetMapping("/{yearId}")
    public ResponseResult getYearAchievementById(@PathVariable Long yearId) {
        try {
            return ResponseBuilder.success(yearAchievementService.getYearAchievementById(yearId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get yearAchievements by id");
        }
    }

    @DeleteMapping("/{yearId}")
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
    public ResponseResult editYearAchievement(@RequestBody AchievementRequestDto params, @PathVariable Long yearId) {
        try {
            return ResponseBuilder.success(yearAchievementService.editYearAchievement(params, yearId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit yearAchievements");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set YearAchievement logo")
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
    @ApiOperation(value = "Delete logo")
    public ResponseResult delete(@RequestParam("yearAchievementId") Long yearAchievementId) {
        try {
            yearAchievementService.deleteLogo(yearAchievementId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}