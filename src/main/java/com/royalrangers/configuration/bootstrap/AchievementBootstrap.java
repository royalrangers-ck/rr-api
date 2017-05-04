package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.achivement.AgeCategory;
import com.royalrangers.model.achievement.QuarterAchievement;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import com.royalrangers.model.achievement.YearAchievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                    threeYearAchievement.setAgeCategory(AgeCategory.BEGINNERS);
                    break;
                }
                case 2: {
                    threeYearAchievement.setAgeCategory(AgeCategory.PIONEERS);
                    break;
                }
                case 3: {
                    threeYearAchievement.setAgeCategory(AgeCategory.PATHFINDERS);
                    break;
                }
                case 4: {
                    threeYearAchievement.setAgeCategory(AgeCategory.RANGERS);
                    break;
                }
            }
            threeYearAchievement.setTwelveYearAchievement(null);
            threeYearAchievement.setLogoUrl(null);
            threeYearAchievements.add(threeYearAchievement);
        });
        return threeYearAchievements;
    }

    public Map<String, Object> createYear() {
        List<YearAchievement> forBeginners = new ArrayList<>();
        List<YearAchievement> forPioneers = new ArrayList<>();
        List<YearAchievement> forPathfinders = new ArrayList<>();
        List<YearAchievement> forRangers = new ArrayList<>();
        Map<String, Object> yearList = new HashMap<>();
        IntStream.range(1, 4).forEach(element -> {
            YearAchievement yearAchievement = new YearAchievement();
            yearAchievement.setName("Нагорода за 1 рік");
            yearAchievement.setDescription("Отримується після виконання квартальних нагород");
            yearAchievement.setThreeYearAchievement(null);
            yearAchievement.setLogoUrl(null);
            forBeginners.add(yearAchievement);
            forPioneers.add(yearAchievement);
            forPathfinders.add(yearAchievement);
            forRangers.add(yearAchievement);
        });
        yearList.put("for_beginners", forBeginners);
        yearList.put("for_pioneers", forPioneers);
        yearList.put("for_pathfinders", forPathfinders);
        yearList.put("for_rangers", forRangers);
        return yearList;
    }

    public Map<String, Object> createQuarter() {
        Map<String, Object> mapQuarter = new HashMap<>();
        List<QuarterAchievement> forYear1 = new ArrayList<>();
        List<QuarterAchievement> forYear2 = new ArrayList<>();
        List<QuarterAchievement> forYear3 = new ArrayList<>();
        IntStream.range(1, 5).forEach(element -> {
            QuarterAchievement quarterAchievement = new QuarterAchievement();
            quarterAchievement.setName("quarterAchievement" + element);
            quarterAchievement.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod" +
                    " Ut enim ad minim veniam.");
            quarterAchievement.setYearAchievement(null);
            quarterAchievement.setLogoUrl(null);
            forYear1.add(quarterAchievement);
            forYear2.add(quarterAchievement);
            forYear3.add(quarterAchievement);
        });
        mapQuarter.put("forYear1", forYear1);
        mapQuarter.put("forYear2", forYear2);
        mapQuarter.put("forYear3", forYear3);
        return mapQuarter;
    }

}
