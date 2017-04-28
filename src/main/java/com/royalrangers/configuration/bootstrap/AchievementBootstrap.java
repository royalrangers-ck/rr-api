package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.dto.achievement.TaskRequestDto;
import com.royalrangers.dto.achievement.TestRequestDto;
import com.royalrangers.dto.achievement.ThreeYearRequestDto;
import com.royalrangers.enums.achivement.RewardMark;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.achievement.*;
import com.royalrangers.repository.achievement.*;
import com.royalrangers.service.achievement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class AchievementBootstrap {





    public List<Reward> createReward(){
        List<Reward> rewards = new ArrayList<>();
        IntStream.range(1, 6).forEach(element -> {
            Reward reward = new Reward();
            switch (element){
                case 1: {
                    reward.setName("Reward" + element);
                    reward.setDescription("Description " + element);
                    reward.setLogoUrl(null);
                    reward.setRewardMark(RewardMark.DEFAULT);
                    reward.setRewardType(RewardType.LATH);
                    reward.setRequirements("Requirements");
                    break;
                }
                case 2: {
                    reward.setName("Reward" + element);
                    reward.setDescription("Description " + element);
                    reward.setLogoUrl(null);
                    reward.setRewardMark(RewardMark.DEFAULT);
                    reward.setRewardType(RewardType.MEDAL);
                    reward.setRequirements("Requirements");
                    break;
                }
                case 3: {
                    reward.setName("Reward" + element);
                    reward.setDescription("Description " + element);
                    reward.setLogoUrl(null);
                    reward.setRewardMark(RewardMark.GOLD);
                    reward.setRewardType(RewardType.STAR);
                    reward.setRequirements("Requirements");
                    break;
                }
                case 4: {
                    reward.setName("Reward" + element);
                    reward.setDescription("Description " + element);
                    reward.setLogoUrl(null);
                    reward.setRewardMark(RewardMark.DEFAULT);
                    reward.setRewardType(RewardType.CAMP);
                    reward.setRequirements("Requirements");
                    break;
                }
                case 5: {
                    reward.setName("Reward" + element);
                    reward.setDescription("Description " + element);
                    reward.setLogoUrl(null);
                    reward.setRewardMark(RewardMark.DEFAULT);
                    reward.setRewardType(RewardType.TRIP);
                    reward.setRequirements("Requirements");
                    break;
                }
            }
            rewards.add(reward);
        });
        return rewards;
    }
    public AchievementRequestDto createTwelveYear(){
        AchievementRequestDto twelveYearAchievement = new AchievementRequestDto();
        twelveYearAchievement.setName("TwelveYear achievement");
        twelveYearAchievement.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        twelveYearAchievement.setLogoUrl(null);
        twelveYearAchievement.setRequirements("requirements");

        return twelveYearAchievement;
    }

    public List<ThreeYearRequestDto> createThreeYear(){
        List<ThreeYearRequestDto> threeYearAchievements = new ArrayList<>();
        IntStream.range(1, 5).forEach(element -> {
            ThreeYearRequestDto threeYearAchievement = new ThreeYearRequestDto();
            threeYearAchievement.setName("threeYearAchievement" + element);
            threeYearAchievement.setDescription("\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                    " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
            threeYearAchievement.setAgeCategory("BEGINNERS");
            threeYearAchievement.setRequirements("requirements");
            threeYearAchievement.setUpLevelId(1);
            threeYearAchievement.setLogoUrl(null);
            threeYearAchievements.add(threeYearAchievement);
        });
        return threeYearAchievements;
    }

    public List<AchievementRequestDto> createYear(){
        List<AchievementRequestDto> yearAchievements = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            AchievementRequestDto yearAchievement = new AchievementRequestDto();
            yearAchievement.setName("yearAchievement" + element);
            yearAchievement.setDescription("\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                    " Ut enim ad minim veniam.");
            yearAchievement.setRequirements("requirements"+ element);
            yearAchievement.setUpLevelId(1);
            yearAchievement.setLogoUrl(null);
            yearAchievements.add(yearAchievement);
        });
        return yearAchievements;
    }

    public List<AchievementRequestDto> createQuarter(){
        List<AchievementRequestDto> quarterAchievements = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            AchievementRequestDto quarterAchievement = new AchievementRequestDto();
            quarterAchievement.setName("quarterAchievement" + element);
            quarterAchievement.setDescription("\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                    " Ut enim ad minim veniam.");
            quarterAchievement.setRequirements("requirements"+ element);
            quarterAchievement.setUpLevelId(1);
            quarterAchievement.setLogoUrl(null);
            quarterAchievements.add(quarterAchievement);
        });
        return quarterAchievements;
    }


    public void achievementInit(){


    }


}
