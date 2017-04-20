package com.royalrangers.controller.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.enums.ImageType;
import com.royalrangers.model.achievement.Reward;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.achievement.RewardService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/achievements/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    public ResponseResult getAllReward() {
        try {
            return ResponseBuilder.success(rewardService.getAllReward());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all rewards");
        }
    }

    @PostMapping
    public ResponseResult addReward(@RequestBody Reward reward) {
        try {
            rewardService.addReward(reward);
            return ResponseBuilder.success("Successful addition of a reward");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add reward");
        }
    }

    @GetMapping("/{rewardId}")
    public ResponseResult getRewardById(@PathVariable Long rewardId) {
        try {
            return ResponseBuilder.success(rewardService.getRewardById(rewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get reward by id");
        }
    }

    @DeleteMapping("/{rewardId}")
    public ResponseResult deleteReward(@PathVariable Long rewardId) {
        try {
            rewardService.deleteReward(rewardId);
            return ResponseBuilder.success("Delete Reward was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed deleted Reward");
        }
    }

    @PutMapping("/{rewardId}")
    public ResponseResult editReward(@RequestBody Reward reward, @PathVariable Long rewardId) {
        try {
            return ResponseBuilder.success(rewardService.editReward(reward, rewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Reward");
        }
    }

    @PostMapping("/logo")
    @ApiOperation(value = "Upload and set Reward logo")
    public ResponseResult uploadLogo(@RequestParam("rewardId") Long rewardId, @RequestParam("file") MultipartFile file) {
        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.REWARD_LOGO);
            rewardService.setLogoUrl(logoUrl, rewardId);
            return ResponseBuilder.success("LogoUrl", logoUrl);
        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @ApiOperation(value = "Delete logo")
    public ResponseResult delete(@RequestParam("rewardId") Long rewardId) {
        try {
            rewardService.deleteLogo(rewardId);
            return ResponseBuilder.success("Logo deleted.");
        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
