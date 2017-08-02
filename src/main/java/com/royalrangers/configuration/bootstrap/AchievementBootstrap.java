package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.achievement.ThreeYearAchievement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AchievementBootstrap {

    public AchievementRequestDto createTwelveYear() {
        AchievementRequestDto twelveYearAchievement = new AchievementRequestDto();
        twelveYearAchievement.setName("TwelveYear achievement");
        twelveYearAchievement.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod" +
                " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        twelveYearAchievement.setLogoUrl(null);
        return twelveYearAchievement;
    }

    public List<ThreeYearAchievement> createThreeYear() {
        List<ThreeYearAchievement> threeYearAchievements = new ArrayList<>();
        IntStream.range(1, 5).forEach(element -> {
            ThreeYearAchievement threeYearAchievement = new ThreeYearAchievement();
            threeYearAchievement.setName("Нагорода за 3 роки");
            threeYearAchievement.setDescription("Видається після отримання 3-x річних нагород");
            switch (element) {
                case 1: {
                    threeYearAchievement.setUserAgeGroup(UserAgeGroup.BEGINNER);
                    break;
                }
                case 2: {
                    threeYearAchievement.setUserAgeGroup(UserAgeGroup.PIONEER);
                    break;
                }
                case 3: {
                    threeYearAchievement.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                    break;
                }
                case 4: {
                    threeYearAchievement.setUserAgeGroup(UserAgeGroup.RANGER);
                    break;
                }
            }
            threeYearAchievement.setTwelveYearAchievement(null);
            threeYearAchievement.setLogoUrl(null);
            threeYearAchievements.add(threeYearAchievement);
        });
        return threeYearAchievements;
    }

}
