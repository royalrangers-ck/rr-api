package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.dto.achievement.ThreeYearRequestDto;

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

    public List<ThreeYearRequestDto> createThreeYear() {
        List<ThreeYearRequestDto> threeYearAchievements = new ArrayList<>();
        IntStream.range(1, 5).forEach(element -> {
            ThreeYearRequestDto threeYearAchievement = new ThreeYearRequestDto();
            threeYearAchievement.setName("threeYearAchievement" + element);
            threeYearAchievement.setDescription("\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod" +
                    " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
            threeYearAchievement.setAgeCategory("BEGINNERS");
            threeYearAchievement.setUpLevelId(1);
            threeYearAchievement.setLogoUrl(null);
            threeYearAchievements.add(threeYearAchievement);
        });
        return threeYearAchievements;
    }

    public List<AchievementRequestDto> createYear() {
        List<AchievementRequestDto> yearAchievements = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            AchievementRequestDto yearAchievement = new AchievementRequestDto();
            yearAchievement.setName("yearAchievement" + element);
            yearAchievement.setDescription("\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod" +
                    " Ut enim ad minim veniam.");
            yearAchievement.setUpLevelId(1);
            yearAchievement.setLogoUrl(null);
            yearAchievements.add(yearAchievement);
        });
        return yearAchievements;
    }

    public List<AchievementRequestDto> createQuarter() {
        List<AchievementRequestDto> quarterAchievements = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            AchievementRequestDto quarterAchievement = new AchievementRequestDto();
            quarterAchievement.setName("quarterAchievement" + element);
            quarterAchievement.setDescription("\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod" +
                    " Ut enim ad minim veniam.");
            quarterAchievement.setUpLevelId(1);
            quarterAchievement.setLogoUrl(null);
            quarterAchievements.add(quarterAchievement);
        });
        return quarterAchievements;
    }

}
