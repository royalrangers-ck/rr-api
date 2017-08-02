package com.royalrangers.configuration.bootstrap;

import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.achievement.QuarterAchievement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class QuarterAchievementBootstrap {
    private void createQuarterForBeginner(List<QuarterAchievement> quarterAchievementList) {
        IntStream.range(1, 4).forEach(yearBeginner -> {
            switch (yearBeginner) {
                case 1: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearBeginner + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 8 завдань.");
                        quarterAchievement.setYearAchievement(null);
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.BEGINNER);
                        quarterAchievement.setLogoUrl(null);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
                case 2: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearBeginner + "-го року");
                        switch (quarter) {
                            case 1: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 8 завдань.");
                                break;
                            }
                            case 2: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 7 завдань.");
                                break;
                            }
                            case 3: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 9 завдань.");
                                break;
                            }
                            case 4: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 9 завдань.");
                                break;
                            }
                        }
                        quarterAchievement.setYearAchievement(null);
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.BEGINNER);
                        quarterAchievement.setLogoUrl(null);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
                case 3: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearBeginner + "-го року");
                        switch (quarter) {
                            case 1: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 10 завдань.");
                                break;
                            }
                            case 2: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 10 завдань.");
                                break;
                            }
                            case 3: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 8 завдань.");
                                break;
                            }
                            case 4: {
                                quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати 10 завдань.");
                                break;
                            }
                        }
                        quarterAchievement.setYearAchievement(null);
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.BEGINNER);
                        quarterAchievement.setLogoUrl(null);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
            }
        });
    }

    private void createQuarterForPathfinder(List<QuarterAchievement> quarterAchievementList) {
        IntStream.range(1, 4).forEach(yearPathfinder -> {
            switch (yearPathfinder) {
                case 1: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearPathfinder + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати один оранжевий тест та один синій або зелений.");
                        quarterAchievement.setYearAchievement(null);
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                        quarterAchievement.setLogoUrl(null);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
                case 2: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearPathfinder + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати один оранжевий тест та один синій або зелений.");
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
                case 3: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearPathfinder + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за синю, червону та зелену чверть потрібно виконати один оранжевий " +
                                "тест та один синій або зелений. Щоб отримати нагороду за жовту чверть потрібно виконати один оранжевий тест.");
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.PATHFINDER);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
            }
        });
    }

    private void createQuarterForPioneer(List<QuarterAchievement> quarterAchievementList) {
        IntStream.range(1, 4).forEach(yearPioneer -> {
            switch (yearPioneer) {
                case 1: {
                    IntStream.range(1, 5).forEach(element -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + yearPioneer + " чверть " + yearPioneer + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати один коричневий тест та один зелений або платиновий.");
                        quarterAchievement.setYearAchievement(null);
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.PIONEER);
                        quarterAchievement.setLogoUrl(null);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
                case 2: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearPioneer + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати один коричневий тест та один зелений або платиновий.");
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.PIONEER);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
                case 3: {
                    IntStream.range(1, 5).forEach(quarter -> {
                        QuarterAchievement quarterAchievement = new QuarterAchievement();
                        quarterAchievement.setName("Нагорода за " + quarter + " чверть " + yearPioneer + "-го року");
                        quarterAchievement.setDescription("Щоб отримати нагороду за чверть потрібно виконати один коричневий тест та один зелений або платиновий. Щоб отримати нагороду за жовту чверть потрібно виконати один коричневий тест.");
                        quarterAchievement.setUserAgeGroup(UserAgeGroup.PIONEER);
                        quarterAchievementList.add(quarterAchievement);
                    });
                    break;
                }
            }
        });
    }

    private void createQuarterForRanger(List<QuarterAchievement> quarterAchievementList) {
        IntStream.range(1, 4).forEach(yearRanger -> {
            IntStream.range(1, 5).forEach(element -> {
                QuarterAchievement quarterAchievement = new QuarterAchievement();
                quarterAchievement.setName("Нагорода за " + yearRanger + " чверть " + yearRanger + "-го року");
                quarterAchievement.setDescription(" ");
                quarterAchievement.setYearAchievement(null);
                quarterAchievement.setUserAgeGroup(UserAgeGroup.RANGER);
                quarterAchievement.setLogoUrl(null);
                quarterAchievementList.add(quarterAchievement);
            });
        });
    }

    public List<QuarterAchievement> createQuarter() {
        List<QuarterAchievement> quarterAchievementList = new ArrayList<>();
        createQuarterForBeginner(quarterAchievementList);
        createQuarterForPathfinder(quarterAchievementList);
        createQuarterForPioneer(quarterAchievementList);
        createQuarterForRanger(quarterAchievementList);
        return quarterAchievementList;
    }
}
