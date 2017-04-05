package com.royalrangers.service.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.achievement.UserTestBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
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

    public ResponseResult getUsersData(String email) {
        User user = userRepository.findByEmail(email);
        List<UserTestBean> list = userTestService.findAllByPlatoon(user.getPlatoon().getId());
        List<UserTestBean> result = new ArrayList<>();
        for (UserTestBean tests : list) {
            if (tests.getAchievementState() == AchievementState.SUBMITTED) {
                result.add(tests);
            }
        }
        return ResponseBuilder.success(result);
    }
}
