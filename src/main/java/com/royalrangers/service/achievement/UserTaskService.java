package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTask;
import com.royalrangers.bean.achievement.TaskBean;
import com.royalrangers.repository.achievement.UserTaskRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserTaskService {

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    public List<TaskBean> getAllForUser() {
        List<UserTask> userTasks = userTaskRepository.findByUserId(userService.getAuthenticatedUserId());
        List<TaskBean> result = new ArrayList<>();
        for (UserTask item : userTasks) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserTask(Map<String, Object> params) {
        UserTask userTask = new UserTask();
        String achievementState = (String) params.get("state");
        userTask.setAchievementState(AchievementState.valueOf(achievementState));
        userTask.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer taskId = (Integer) params.get("taskId");
        userTask.setTask(taskService.getTaskById(taskId.longValue()));
        userTaskRepository.saveAndFlush(userTask);
    }

    public TaskBean getUserTaskById(Long id) {
        UserTask user = userTaskRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public void deleteUserTask(Long id) {
        userTaskRepository.delete(id);
    }

    public void editUserTask(Map<String, Object> params, Long id) {
        UserTask savedUserTask = userTaskRepository.findOne(id);
        savedUserTask.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        savedUserTask.setAchievementState(AchievementState.valueOf(achievementState));
        Integer taskId = (Integer) params.get("taskId");
        savedUserTask.setTask(taskService.getTaskById(taskId.longValue()));
        userTaskRepository.saveAndFlush(savedUserTask);
    }

    private TaskBean buildUserAchievementBean(UserTask item) {
        TaskBean userAchievementBean = new TaskBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUserId(userBean.getId());
        userAchievementBean.setUserFirstName(userBean.getFirstName());
        userAchievementBean.setUserLastName(userBean.getLastName());
        userAchievementBean.setUserPlatoonId(userBean.getPlatoonId());
        userAchievementBean.setUserAvatarUrl(userBean.getUserAvatarUrl());
        userAchievementBean.setTaskId(item.getTask().getId());
        userAchievementBean.setTaskName(item.getTask().getName());
        userAchievementBean.setTaskDescription(item.getTask().getDescription());
        return userAchievementBean;
    }

}
