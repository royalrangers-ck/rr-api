package com.royalrangers.controller.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.UserBean;
import com.royalrangers.model.achievement.*;
import com.royalrangers.service.achievement.*;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseResult getUsersListForTask(Long platoonId, Long taskId, String achievementState){
        List<UserTask> userAchievementList = userTaskService.getUserTasksByTaskId(taskId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/test")
    public ResponseResult getUsersListForTest(Long platoonId, Long testId, String achievementState){
        List<UserTest> userAchievementList = userTestService.getUserTestsByTestId(testId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/quarterAchievement")
    public ResponseResult getUsersListForQuarterAchievement(Long platoonId, Long achievementId, String achievementState){
        List<UserQuarterAchievement> userAchievementList = userQuarterAchievementService.getUserQuarterAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/yearAchievement")
    public ResponseResult getUsersListForYearAchievement(Long platoonId, Long achievementId, String achievementState){
        List<UserYearAchievement> userAchievementList = userYearAchievementService.getUserYearAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/threeYearAchievement")
    public ResponseResult getUsersListForThreeYearAchievement(Long platoonId, Long achievementId, String achievementState){
        List<UserThreeYearAchievement> userAchievementList = userThreeYearAchievementService.getUserThreeYearAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }

    @GetMapping("/twelveYearAchievement")
    public ResponseResult getUsersListForTwelveYearAchievement(Long platoonId, Long achievementId, String achievementState){
        List<UserTwelveYearAchievement> userAchievementList = userTwelveYearAchievementService.getUserTwelveYearAchievementByAchievementId(achievementId);
        List<UserBean> result = getUsersFromUserAchievements(userAchievementList, platoonId,achievementState);
        return ResponseBuilder.success(result);
    }
}
