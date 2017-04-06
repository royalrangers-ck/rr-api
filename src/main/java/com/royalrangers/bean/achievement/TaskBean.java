package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private AchievementState achievementState;
    private Long taskId;
    private String taskName;
    private String taskDescription;
}
