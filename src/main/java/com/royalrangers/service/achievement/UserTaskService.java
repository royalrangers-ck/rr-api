package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTask;
import com.royalrangers.repository.achievement.UserTaskRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserTaskService {

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    public List<UserTask> getAllForUser() {
        return userTaskRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public void addUserTask(UserAchievementRequestDto params) {
        UserTask userTask = new UserTask();
        String achievementState = params.getState();
        userTask.setAchievementState(AchievementState.valueOf(achievementState));
        userTask.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer taskId = params.getId();
        userTask.setTask(taskService.getTaskById(taskId.longValue()));
        userTaskRepository.saveAndFlush(userTask);
    }

    public UserTask getUserTaskById(Long id) {
        return  userTaskRepository.findOne(id);
    }

    public List<UserTask> getUserTasksByTaskId(Long taskId) {
        return userTaskRepository.findAllByTask(taskId);
    }

    public void deleteUserTask(Long id) {
        userTaskRepository.delete(id);
    }

    public void editUserTask(UserAchievementRequestDto params, Long id) {
        UserTask savedUserTask = userTaskRepository.findOne(id);
        savedUserTask.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserTask.setAchievementState(AchievementState.valueOf(achievementState));
        Integer taskId = params.getId();
        savedUserTask.setTask(taskService.getTaskById(taskId.longValue()));
        userTaskRepository.saveAndFlush(savedUserTask);
    }
}
