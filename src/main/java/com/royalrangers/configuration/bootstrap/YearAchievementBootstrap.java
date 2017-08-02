package com.royalrangers.configuration.bootstrap;

import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.achievement.YearAchievement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class YearAchievementBootstrap {
    private void createYearForBeginner(List<YearAchievement> yearAchievementList) {
        IntStream.range(1, 4).forEach(element -> {
            YearAchievement yearForBeginners = new YearAchievement();
            yearForBeginners.setName("Нагорода за " + element + " рік.");
            yearForBeginners.setDescription("Щоб отримати нагороду за рік (бронзовий, срібний. золотий) потрібно зібрати 4 нагороди за чверть (синю, червону, зелену, жовту).");
            yearForBeginners.setThreeYearAchievement(null);
            yearForBeginners.setLogoUrl(null);
            yearForBeginners.setUserAgeGroup(UserAgeGroup.BEGINNER);
            yearAchievementList.add(yearForBeginners);
        });
    }

    private void createYearForPathfinder(List<YearAchievement> yearAchievementList) {
        IntStream.range(1, 4).forEach(element -> {
            YearAchievement yearForPathfinders = new YearAchievement();
            switch (element) {
                case 1: {
                    yearForPathfinders.setName("Перший рік.");
                    yearForPathfinders.setDescription("Щоб отримати нагороду за рік (бронзовий рік) потрібно зібрати 4 нагороди за чверть (синю, " +
                            "червону, зелену, жовту) + виконати додатково один рекомендований синій тест та один тест " +
                            "лідерства червоний або жовтий + потрібно виконати додаткові вимоги (Recite again from memory " +
                            "the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule)");
                    yearForPathfinders.setThreeYearAchievement(null);
                    yearForPathfinders.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                    yearForPathfinders.setLogoUrl(null);
                    break;
                }
                case 2: {
                    yearForPathfinders.setName("Другий рік.");
                    yearForPathfinders.setDescription("Щоб отримати нагороду за рік (срібний рік) потрібно зібрати 4 нагороди за чверть (синю, червону, " +
                            "зелену, жовту) + виконати додатково один рекомендований синій тест та один тест лідерства " +
                            "червоний або жовтий + потрібно виконати додаткові вимоги:" +
                            "\uF0B7 Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule." +
                            "\uF0B7 Be a Bronze Square Knot award recipient at least six months. " +
                            "\uF0B7 Serve in one or more leadership positions in Discovery Rangers for at least six months. " +
                            "\uF0B7 Show the ability to present the plan of salvation to someone");
                    yearForPathfinders.setThreeYearAchievement(null);
                    yearForPathfinders.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                    yearForPathfinders.setLogoUrl(null);
                    break;
                }
                case 3: {
                    yearForPathfinders.setName("Третій рік");
                    yearForPathfinders.setDescription("Щоб отримати нагороду за рік (золотий рік) потрібно зібрати 4 нагороди за чверть (синю, червону, " +
                            "зелену, жовту) + виконати додатково один рекомендований синій тест та два тести лідерства " +
                            "червоний або жовтий + потрібно виконати додаткові вимоги:" +
                            "• Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule " +
                            "• Be a Silver Sheet Bend award recipient for no less than nine months, or be at least nine years old." +
                            "• Serve in one or more leadership positions in Discovery Rangers for an additional six months, for a total of twelve months." +
                            "• Show the ability to present the plan of salvation to someone.");
                    yearForPathfinders.setThreeYearAchievement(null);
                    yearForPathfinders.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                    yearForPathfinders.setLogoUrl(null);
                    break;
                }
            }
            yearAchievementList.add(yearForPathfinders);
        });
    }

    private void createYearForPioneer(List<YearAchievement> yearAchievementList) {
        IntStream.range(1, 4).forEach(element -> {
            YearAchievement yearForPioneer = new YearAchievement();
            switch (element) {
                case 1: {
                    yearForPioneer.setName("Нагорода за " + element + " рік.");
                    yearForPioneer.setDescription("Щоб отримати нагороду за рік (бронзовий рік) потрібно зібрати 4 нагороди за чверть (синю, " +
                            "червону, зелену, жовту) + виконати додатково один рекомендований зелений тест та один тест " +
                            "лідерства (червоний або жовтий або голубий) + потрібно виконати додаткові вимоги (Recite again " +
                            "from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule)");
                    yearForPioneer.setThreeYearAchievement(null);
                    yearForPioneer.setUserAgeGroup(UserAgeGroup.PIONEER);
                    yearForPioneer.setLogoUrl(null);
                    break;
                }
                case 2: {
                    yearForPioneer.setName("Нагорода за " + element + " рік.");
                    yearForPioneer.setDescription("Щоб отримати нагороду за рік (бронзовий рік) потрібно зібрати 4 нагороди за чверть (синю, " +
                            "червону, зелену, жовту) + виконати додатково один рекомендований зелений тест та один тест " +
                            "лідерства (червоний або жовтий або голубий) + потрібно виконати додаткові вимоги:" +
                            "• Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule." +
                            "• Be a Bronze Clove Hitch recipient at least six months." +
                            "• Serve in one or more leadership positions in Adventure Rangers for at least six months." +
                            "• Demonstrate the ability to present the plan of salvation to someone.");
                    yearForPioneer.setThreeYearAchievement(null);
                    yearForPioneer.setUserAgeGroup(UserAgeGroup.PIONEER);
                    yearForPioneer.setLogoUrl(null);
                    break;
                }
                case 3: {
                    yearForPioneer.setName("Нагорода за " + element + " рік.");
                    yearForPioneer.setDescription("Щоб отримати нагороду за рік (золотий рік) потрібно зібрати 4 нагороди за чверть (синю, червону, " +
                            "зелену, жовту) + виконати додатково один рекомендований зелений тест та два тести лідерства " +
                            "(червоний або жовтий або голубий) + потрібно виконати додаткові вимоги:" +
                            "• Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule." +
                            "• Be a Silver Timber Hitch recipient for no less than nine months." +
                            "• Be at least twelve years old, and younger than eighteen years old." +
                            "• Serve in one or more leadership positions in Adventure Rangers for an additional six months, for a total of twelve months." +
                            "• Demonstrate the ability to present the plan of salvation to someone.");
                    yearForPioneer.setThreeYearAchievement(null);
                    yearForPioneer.setUserAgeGroup(UserAgeGroup.PIONEER);
                    yearForPioneer.setLogoUrl(null);
                    break;
                }
            }
            yearAchievementList.add(yearForPioneer);
        });
    }

    private void createYearForRanger(List<YearAchievement> yearAchievementList) {
        IntStream.range(1, 4).forEach(element -> {
            YearAchievement yearForRangers = new YearAchievement();
            switch (element) {
                case 1: {
                    yearForRangers.setName("Нагорода за " + element + " рік.");
                    yearForRangers.setDescription("Щоб отримати нагороду за перший рік (E1) потрібно виконати один ДУХОВНИЙ ВИКЛИК " +
                            "(синій) та чотири платинових тести на вибір + один платиновий обов’язковий тест + один " +
                            "тест лідерства (жовтий чи голубий) + потрібно виконати додаткові вимоги: " +
                            "\uF0B7 Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points  of the Emblem, and the Golden Rule.");
                    yearForRangers.setThreeYearAchievement(null);
                    yearForRangers.setUserAgeGroup(UserAgeGroup.RANGER);
                    yearForRangers.setLogoUrl(null);
                    break;
                }
                case 2: {
                    yearForRangers.setName("Нагорода за " + element + " рік.");
                    yearForRangers.setDescription("Щоб отримати нагороду за другий рік (E2) потрібно виконати один ДУХОВНИЙ ВИКЛИК " +
                            "(червоний) та чотири платинових тести на вибір + один платиновий обов’язковий тест  один тест лідерства + (жовтий чи голубий)" +
                            " + потрібно виконати додаткові вимоги:" +
                            "• Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule." +
                            "• Be an E1 Award recipient at least six months." +
                            "• Serve in one or more leadership positions in Expedition Rangers for at least six months." +
                            "• Demonstrate the ability to present the plan of salvation to someone.");
                    yearForRangers.setThreeYearAchievement(null);
                    yearForRangers.setUserAgeGroup(UserAgeGroup.RANGER);
                    yearForRangers.setLogoUrl(null);
                    break;
                }
                case 3: {
                    yearForRangers.setName("Нагорода за " + element + " рік.");
                    yearForRangers.setDescription("Щоб отримати нагороду за третій рік (E3) потрібно виконати один ДУХОВНИЙ ВИКЛИК " +
                            "(зелений) та чотири платинових тести на вибір + один платиновий обов’язковий тест + два тести лідерства (жовтий чи голубий) " +
                            "+ потрібно виконати додаткові вимоги:" +
                            "• Recite again from memory the Royal Rangers Pledge, Code, Motto, the meaning of the points of the Emblem, and the Golden Rule." +
                            "• Be an E2 Award recipient for no less than nine months." +
                            "• Serve in one or more leadership positions in Expedition Rangers for an additional six months, for a total of twelve months." +
                            "• Demonstrate the ability to present the plan of salvation to someone.");
                    yearForRangers.setThreeYearAchievement(null);
                    yearForRangers.setUserAgeGroup(UserAgeGroup.RANGER);
                    yearForRangers.setLogoUrl(null);
                    break;
                }
            }
            yearAchievementList.add(yearForRangers);
        });
    }

    public List<YearAchievement> createYear() {
        List<YearAchievement> yearAchievementList = new ArrayList<>();
        createYearForBeginner(yearAchievementList);
        createYearForPathfinder(yearAchievementList);
        createYearForPioneer(yearAchievementList);
        createYearForRanger(yearAchievementList);
        return yearAchievementList;
    }
}
