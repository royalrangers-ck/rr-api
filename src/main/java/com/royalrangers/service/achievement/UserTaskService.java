package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTask;
import com.royalrangers.bean.achievement.UserTaskBean;
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

    public List<UserTaskBean> getAllForUser() {
        List<UserTask> userTasks = userTaskRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserTaskBean> result = new ArrayList<>();
        for (UserTask item : userTasks) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserTask(Map<String, Object> params) {
        UserTask userTask = new UserTask();
        userTask.setCreateDate(new Date());
        userTask.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        userTask.setAchievementState(AchievementState.valueOf(achievementState));
        userTask.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer taskId = (Integer) params.get("taskId");
        userTask.setTask(taskService.getTaskById(taskId.longValue()));
        userTaskRepository.saveAndFlush(userTask);
    }

    public UserTaskBean getUserTaskById(Long id) {
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

    private UserTaskBean buildUserAchievementBean(UserTask item) {
        UserTaskBean userAchievementBean = new UserTaskBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setTask(item.getTask());
        return userAchievementBean;
    }

}
