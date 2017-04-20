package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.TwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/achievements/twelveYear")
public class TwelveYearAchievementController {

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    public ResponseResult getAllTwelveYearAchievement() {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.getAllTwelveYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get TwelveYearAchievement");
        }
    }

    @PostMapping
    public ResponseResult addTwelveYearAchievement(@RequestBody AchievementRequestDto params) {
        try {
            twelveYearAchievementService.addTwelveYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a TwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add TwelveYearAchievement");
        }
    }

    @GetMapping("/{twelveYearId}")
    public ResponseResult getTwelveYearAchievementById(@PathVariable Long twelveYearId) {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get TwelveYearAchievement by id");
        }
    }

    @DeleteMapping("/{twelveYearId}")
    public ResponseResult deleteTwelveYearAchievement(@PathVariable Long twelveYearId) {
        try {
            twelveYearAchievementService.deleteTwelveYearAchievement(twelveYearId);
            return ResponseBuilder.success("Successful delete TwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete TwelveYearAchievement");
        }
    }

    @PutMapping("/{twelveYearId}")
    public ResponseResult editTwelveYearAchievement(@RequestBody AchievementRequestDto params, @PathVariable Long twelveYearId) {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.editTwelveYearAchievement(params, twelveYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete TwelveYearAchievement");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set TwelveYearAchievement logo")
    public ResponseResult uploadLogo(@RequestParam("twelveYearAchievementId") Long twelveYearAchievementId, @RequestParam("file") MultipartFile file) {
        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.TWELVE_YEAR_ACHIEVEMENT_LOGO);
            twelveYearAchievementService.setLogoUrl(logoUrl, twelveYearAchievementId);
            return ResponseBuilder.success("LogoUrl", logoUrl);
        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @ApiOperation(value = "Delete logo")
    public ResponseResult delete(@RequestParam("twelveYearAchievementId") Long twelveYearAchievementId) {
        try {
            twelveYearAchievementService.deleteLogo(twelveYearAchievementId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

}