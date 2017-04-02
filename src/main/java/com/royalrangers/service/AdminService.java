package com.royalrangers.service;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.User;
import com.royalrangers.repository.AuthorityRepository;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserTwelveYearAchievementService userTwelveYearAchievementService;
    @Autowired
    private AuthorityRepository authorityRepository;

    public ResponseResult getUsersData(User user) {

        if (user.getAuthorities().contains(authorityRepository.findOne(2L))) {

            return ResponseBuilder.success(userTwelveYearAchievementService.getAllUserWithAchievement());
        }
        return ResponseBuilder.fail("This information can see only admin");
    }
}
