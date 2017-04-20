package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTask;
import com.royalrangers.dto.achievement.UserTaskResponseDto;
import com.royalrangers.repository.achievement.UserTaskRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UserTaskResponseDto> getAllForUser() {
        List<UserTask> userTasks = userTaskRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserTaskResponseDto> result = new ArrayList<>();
        for (UserTask item : userTasks) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
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

    public UserTaskResponseDto getUserTaskById(Long id) {
        UserTask user = userTaskRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public List<UserTask> getUserTasksByTaskId(Long taskId) {
        List<UserTask> resultList = userTaskRepository.findAllByTask(taskId);
        return resultList;
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

    private UserTaskResponseDto buildUserAchievementBean(UserTask item) {
        UserTaskResponseDto userAchievementBean = new UserTaskResponseDto();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
//TODO
//        userAchievementBean.setUser(UserService.buildUserAchievementBean(item.getUser()));
        userAchievementBean.setTaskName(item.getTask().getName());
        userAchievementBean.setTaskDescription(item.getTask().getDescription());
        return userAchievementBean;
    }

}
