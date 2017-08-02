package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.ThreeYearRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.ThreeYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/achievements/threeYear")
public class ThreeYearAchievementController {

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    @ApiOperation(value = "Get list of all three-year achievements")
    public ResponseResult getAllThreeYearAchievement() {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getAllThreeYearAchievement());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all threeYearAchievements");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add three-year achievement related to twelve-year achievement")
    public ResponseResult addThreeYearAchievement(@RequestBody ThreeYearRequestDto params) {
        try {
            log.info("Add ThreeYearAchievement " + params.getName());
            return ResponseBuilder.success(threeYearAchievementService.addThreeYearAchievement(params));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add threeYearAchievements");
        }
    }

    @GetMapping("/{threeYearId}")
    @ApiOperation(value = "Get three-year achievement")
    public ResponseResult getThreeYearAchievementById(@PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getThreeYearAchievementById(threeYearId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get threeYearAchievements by id");
        }
    }

    @DeleteMapping("/{threeYearId}")
    @ApiOperation(value = "Delete three-year achievement")
    public ResponseResult deleteThreeYearAchievement(@PathVariable Long threeYearId) {
        try {
            threeYearAchievementService.deleteThreeYearAchievement(threeYearId);
            return ResponseBuilder.success("Successful delete threeYearAchievements");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete threeYearAchievements");
        }
    }

    @PutMapping("/{threeYearId}")
    @ApiOperation(value = "Update the three-year achievement")
    public ResponseResult editThreeYearAchievement(@RequestBody ThreeYearRequestDto params, @PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.editThreeYearAchievement(params, threeYearId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit threeYearAchievements");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set three-year achievement logo")
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
    @ApiOperation(value = "Delete three-year achievement logo")
    public ResponseResult delete(@RequestParam("threeYearAchievementId") Long threeYearAchievementId) {
        try {
            threeYearAchievementService.deleteLogo(threeYearAchievementId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}