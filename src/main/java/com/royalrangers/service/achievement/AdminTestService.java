package com.royalrangers.service.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.UserTest;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.UserService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminTestService {

    @Autowired
    private UserTestService userTestService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public ResponseResult getUsersData() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserTest> list = userTestService.findAllByPlatoon(user.getPlatoon().getId());
        List<UserTest> result = new ArrayList<>();
        for (UserTest tests : list) {
            if (tests.getAchievementState() == AchievementState.SUBMITTED) {
                result.add(tests);
            }
        }
        return ResponseBuilder.success(result);
    }
}
