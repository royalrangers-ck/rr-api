package com.royalrangers.service.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.achievement.UserTestBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.service.achievement.UserTestService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestForAdminService {
    @Autowired
    UserTestService userTestService;
    public ResponseResult getUsersData(User user) {
        List<UserTestBean> list = userTestService.findAllForUser();

        for (UserTestBean tests : list) {
            if (tests.getAchievementState() != AchievementState.SUBMITTED && !tests.getUser().getPlatoonId().equals(user.getPlatoon().getId())) {
                list.remove(tests);
            }
        }
        return ResponseBuilder.success(list);
    }
}
