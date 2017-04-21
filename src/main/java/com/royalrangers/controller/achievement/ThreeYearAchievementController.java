package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.ThreeYearRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.ThreeYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/achievements/threeYear")
public class ThreeYearAchievementController {

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    public ResponseResult getAllThreeYearAchievement() {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getAllThreeYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all threeYearAchievements");
        }
    }

    @PostMapping
    public ResponseResult addThreeYearAchievement(@RequestBody ThreeYearRequestDto params) {
        try {
            threeYearAchievementService.addThreeYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a threeYearAchievements");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add threeYearAchievements");
        }
    }

    @GetMapping("/{threeYearId}")
    public ResponseResult getThreeYearAchievementById(@PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getThreeYearAchievementById(threeYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get threeYearAchievements by id");
        }
    }

    @DeleteMapping("/{threeYearId}")
    public ResponseResult deleteThreeYearAchievement(@PathVariable Long threeYearId) {
        try {
            threeYearAchievementService.deleteThreeYearAchievement(threeYearId);
            return ResponseBuilder.success("Successful delete threeYearAchievements");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete threeYearAchievements");
        }
    }

    @PutMapping("/{threeYearId}")
    public ResponseResult editThreeYearAchievement(@RequestBody ThreeYearRequestDto params, @PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.editThreeYearAchievement(params, threeYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit threeYearAchievements");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set ThreeYearAchievement logo")
    public ResponseResult uploadLogo(@RequestParam("threeYearAchievementId") Long threeYearAchievementId, @RequestParam("file") MultipartFile file) {
        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.THREE_YEAR_ACHIEVEMENT_LOGO);
            threeYearAchievementService.setLogoUrl(logoUrl, threeYearAchievementId);
            return ResponseBuilder.success("LogoUrl", logoUrl);
        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @ApiOperation(value = "Delete logo")
    public ResponseResult delete(@RequestParam("threeYearAchievementId") Long threeYearAchievementId) {
        try {
            threeYearAchievementService.deleteLogo(threeYearAchievementId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}