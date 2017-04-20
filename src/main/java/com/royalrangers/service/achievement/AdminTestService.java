package com.royalrangers.service.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserTestResponseDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
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
        List<UserTestResponseDto> list = userTestService.findAllByPlatoon(user.getPlatoon().getId());
        List<UserTestResponseDto> result = new ArrayList<>();
        for (UserTestResponseDto tests : list) {
            if (tests.getAchievementState() == AchievementState.SUBMITTED) {
                result.add(tests);
            }
        }
        return ResponseBuilder.success(result);
    }
}
