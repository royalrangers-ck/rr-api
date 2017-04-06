package com.royalrangers.controller.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.UserBean;
import com.royalrangers.model.achievement.*;
import com.royalrangers.service.achievement.*;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.royalrangers.service.achievement.UserAchievementService.getUsersFromUserAchievements;

@RestController
@RequestMapping("/achievements/userAchievement")
public class UserAchievementController {

    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private UserTestService userTestService;
    @Autowired
    private UserQuarterAchievementService userQuarterAchievementService;
    @Autowired
    private UserYearAchievementService userYearAchievementService;
    @Autowired
    private UserThreeYearAchievementService userThreeYearAchievementService;
    @Autowired
    private UserTwelveYearAchievementService userTwelveYearAchievementService;



    @GetMapping("/task")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUsersListForTask(@RequestParam Long platoonId,
                                              @RequestParam Long taskId,
                                              @RequestParam String achievementState){
        List<UserTask> userAchievementList = userTaskService.getUserTasksByTaskId(taskId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUsersListForTest(@RequestParam Long platoonId,
                                              @RequestParam Long testId,
                                              @RequestParam String achievementState){
        List<UserTest> userAchievementList = userTestService.getUserTestsByTestId(testId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/quarterAchievement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUsersListForQuarterAchievement(@RequestParam Long platoonId,
                                                            @RequestParam Long achievementId,
                                                            @RequestParam String achievementState){
        List<UserQuarterAchievement> userAchievementList = userQuarterAchievementService.getUserQuarterAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/yearAchievement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUsersListForYearAchievement(@RequestParam Long platoonId,
                                                         @RequestParam Long achievementId,
                                                         @RequestParam String achievementState){
        List<UserYearAchievement> userAchievementList = userYearAchievementService.getUserYearAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/threeYearAchievement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUsersListForThreeYearAchievement(@RequestParam Long platoonId,
                                                              @RequestParam Long achievementId,
                                                              @RequestParam String achievementState){
        List<UserThreeYearAchievement> userAchievementList = userThreeYearAchievementService.getUserThreeYearAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/twelveYearAchievement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getUsersListForTwelveYearAchievement(@RequestParam Long platoonId,
                                                               @RequestParam Long achievementId,
                                                               @RequestParam String achievementState){
        List<UserTwelveYearAchievement> userAchievementList = userTwelveYearAchievementService.getUserTwelveYearAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }
}
