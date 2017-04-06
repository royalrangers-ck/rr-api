package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.AchievementRequestDTO;
import com.royalrangers.service.achievement.QuarterAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements/quarter")
public class QuarterAchievementController {

    @Autowired
    private QuarterAchievementService quarterAchievementService;


    @GetMapping
    public ResponseResult getAllQuarterAchievement() {
        try {
            return ResponseBuilder.success(quarterAchievementService.getAllQuarterAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed added QuarterAchievement");
        }
    }

    @PostMapping
    public ResponseResult addQuarterAchievement(@RequestBody AchievementRequestDTO params) {
        try {
            quarterAchievementService.addQuarterAchievement(params);
            return ResponseBuilder.success("Adding QuarterAchievement was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed added QuarterAchievement");
        }
    }

    @GetMapping("/{quarterId}")
    public ResponseResult getQuarterAchievementById(@PathVariable Long quarterId) {
        try {
            return ResponseBuilder.success(quarterAchievementService.getQuarterAchievementById(quarterId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get QuarterAchievement by id");
        }
    }

    @DeleteMapping("/{quarterId}")
    public ResponseResult deleteQuarterAchievement(@PathVariable Long quarterId) {
        try {
            quarterAchievementService.deleteQuarterAchievement(quarterId);
            return ResponseBuilder.success("Delete QuarterAchievement was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed deleted QuarterAchievement");
        }
    }

    @PutMapping("/{quarterId}")
    public ResponseResult editQuarterAchievement(@RequestBody AchievementRequestDTO params, @PathVariable Long quarterId) {
        try {
            return ResponseBuilder.success(quarterAchievementService.editQuarterAchievement(params, quarterId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit QuarterAchievement");
        }
    }
}