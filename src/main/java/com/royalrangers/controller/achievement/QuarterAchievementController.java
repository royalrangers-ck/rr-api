package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.QuarterAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/achievements/quarter")
public class QuarterAchievementController {

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    @ApiOperation(value = "Get list of all quarter-year achievements")
    public ResponseResult getAllQuarterAchievement() {
        try {
            return ResponseBuilder.success(quarterAchievementService.getAllQuarterAchievement());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed added QuarterAchievement");
        }
    }

    @ApiOperation(value = "Add quarter-year achievement related to year achievement")
    @PostMapping
    public ResponseResult addQuarterAchievement(@RequestBody AchievementRequestDto params) {
        try {
            log.info("Add QuarterAchievement " + params.getName());
            return ResponseBuilder.success(quarterAchievementService.addQuarterAchievement(params));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed added QuarterAchievement");
        }
    }

    @GetMapping("/{quarterId}")
    @ApiOperation(value = "Get quarter-year achievement info")
    public ResponseResult getQuarterAchievementById(@PathVariable Long quarterId) {
        try {
            return ResponseBuilder.success(quarterAchievementService.getQuarterAchievementById(quarterId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get QuarterAchievement by id");
        }
    }

    @DeleteMapping("/{quarterId}")
    @ApiOperation(value = "Delete quarter-year achievement")
    public ResponseResult deleteQuarterAchievement(@PathVariable Long quarterId) {
        try {
            quarterAchievementService.deleteQuarterAchievement(quarterId);
            return ResponseBuilder.success("Delete QuarterAchievement was a success");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed deleted QuarterAchievement");
        }
    }

    @PutMapping("/{quarterId}")
    @ApiOperation(value = "Update quarter-year achievement")
    public ResponseResult editQuarterAchievement(@RequestBody AchievementRequestDto params, @PathVariable Long quarterId) {
        try {
            return ResponseBuilder.success(quarterAchievementService.editQuarterAchievement(params, quarterId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit QuarterAchievement");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set quarter-year achievement logo")
    public ResponseResult uploadLogo(@RequestParam("quarterId") Long quarterId, @RequestParam("file") MultipartFile file) {
        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.QUARTER_ACHIEVEMENT_LOGO);
            quarterAchievementService.setLogoUrl(logoUrl, quarterId);
            return ResponseBuilder.success("LogoUrl", logoUrl);
        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @ApiOperation(value = "Delete quarter-year achievement logo")
    public ResponseResult delete(@RequestParam("quarterId") Long quarterId) {
        try {
            quarterAchievementService.deleteLogo(quarterId);
            return ResponseBuilder.success("Logo deleted.");

        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

}