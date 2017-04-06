package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.AchievementRequestDTO;
import com.royalrangers.service.achievement.ThreeYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements/threeYear")
public class ThreeYearAchievementController {

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @GetMapping
    public ResponseResult getAllThreeYearAchievement() {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getAllThreeYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all threeYearAchievements");
        }
    }

    @PostMapping
    public ResponseResult addThreeYearAchievement(@RequestBody AchievementRequestDTO params) {
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
    public ResponseResult editThreeYearAchievement(@RequestBody AchievementRequestDTO params, @PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.editThreeYearAchievement(params, threeYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit threeYearAchievements");
        }
    }
}