package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskBean {
    private Long id;
    private AchievementState achievementState;
    private AchievementBean user;
    private Task task;
}
