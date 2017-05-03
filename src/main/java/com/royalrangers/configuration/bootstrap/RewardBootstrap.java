package com.royalrangers.configuration.bootstrap;

import com.royalrangers.enums.achivement.RewardMark;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.achievement.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RewardBootstrap {
    public List<Reward> createReward() {
        List<Reward> rewards = new ArrayList<>();
        IntStream.range(1, 15).forEach(element -> {
            Reward medal = new Reward();
            switch (element) {
                case 1: {
                    medal.setName("Medal of Valor");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 2: {
                    medal.setName("Outstanding Courage Medal");
                    medal.setDescription("");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 3: {
                    medal.setName("Honor Gold Medal of Achievement");
                    medal.setDescription("Description");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 4: {
                    medal.setName("Global Leadership Award Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 5: {
                    medal.setName("National Meritorious Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 6: {
                    medal.setName("National Outstanding Service Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 7: {
                    medal.setName("National Leadership Award Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 8: {
                    medal.setName("Organizational Executive Leadership Award Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 9: {
                    medal.setName("Organizational Staff Leadership Award Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 10: {
                    medal.setName("Pastor’s Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 11: {
                    medal.setName("Historical training medal (LMA)");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 12: {
                    medal.setName("Trail of the Saber Medal");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 13: {
                    medal.setName("Highest advancement medal earned as a boy");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
                case 14: {
                    medal.setName("Historical Gold Medal of Achievement");
                    medal.setDescription(" ");
                    medal.setLogoUrl(null);
                    medal.setRewardMark(RewardMark.DEFAULT);
                    medal.setRewardType(RewardType.MEDAL);
                    break;
                }
            }
            rewards.add(medal);
        });
        IntStream.range(1, 7).forEach(element -> {
            Reward star = new Reward();
            switch (element) {
                case 1: {
                    star.setName("Star gold 1");
                    star.setDescription(" ");
                    star.setLogoUrl(null);
                    star.setRewardType(RewardType.STAR);
                    star.setRewardMark(RewardMark.GOLD);
                    break;
                }
                case 2: {
                    star.setName("Star gold 2");
                    star.setDescription("");
                    star.setLogoUrl(null);
                    star.setRewardType(RewardType.STAR);
                    star.setRewardMark(RewardMark.GOLD);
                    break;
                }
                case 3: {
                    star.setName("Star silver 1");
                    star.setDescription(" ");
                    star.setLogoUrl(null);
                    star.setRewardType(RewardType.STAR);
                    star.setRewardMark(RewardMark.SILVER);
                    break;
                }
                case 4: {
                    star.setName("Star silver 2");
                    star.setDescription(" ");
                    star.setLogoUrl(null);
                    star.setRewardType(RewardType.STAR);
                    star.setRewardMark(RewardMark.SILVER);
                    break;
                }
                case 5: {
                    star.setName("Star bronze 1");
                    star.setDescription(" ");
                    star.setLogoUrl(null);
                    star.setRewardType(RewardType.STAR);
                    star.setRewardMark(RewardMark.BRONZE);
                    break;
                }
                case 6: {
                    star.setName("Star bronze 2");
                    star.setDescription(" ");
                    star.setLogoUrl(null);
                    star.setRewardType(RewardType.STAR);
                    star.setRewardMark(RewardMark.BRONZE);
                    break;
                }
            }
            rewards.add(star);
        });
        IntStream.range(1, 25).forEach(element -> {
            Reward ribbon = new Reward();
            switch (element) {
                case 1: {
                    ribbon.setName("Ribbon of Valor");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 2: {
                    ribbon.setName("Outstanding Courage Ribbon");
                    ribbon.setDescription("");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 3: {
                    ribbon.setName("Honor Gold Medal of Achievement Ribbon");
                    ribbon.setDescription("Description");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 4: {
                    ribbon.setName("Global Leadership Award Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 5: {
                    ribbon.setName("National Meritorious Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 6: {
                    ribbon.setName("National Outstanding Service Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 7: {
                    ribbon.setName("Medal of Excellence Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 8: {
                    ribbon.setName("Missions Project Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 9: {
                    ribbon.setName("National Executive Leadership Award Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 10: {
                    ribbon.setName("National Staff Leadership Award Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 11: {
                    ribbon.setName("Historical Leader’s Gold Star Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 12: {
                    ribbon.setName("National Leadership Award Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 13: {
                    ribbon.setName("Historical Leader’s Gold Eagle Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 14: {
                    ribbon.setName("Outstanding Service Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 15: {
                    ribbon.setName("Organizational Executive Leadership Award Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 16: {
                    ribbon.setName("Organizational Staff Leadership Award Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 17: {
                    ribbon.setName("Historical Silver Eagle or Silver Cluster Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 18: {
                    ribbon.setName("Historical Gold Cluster or Blue Cluster Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 19: {
                    ribbon.setName("Pastor’s Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 20: {
                    ribbon.setName("Outpost Coordinator’s Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 21: {
                    ribbon.setName("Outpost Leadership Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 22: {
                    ribbon.setName("Outpost Leader’s Service Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 23: {
                    ribbon.setName("Historical training ribbon (LMA)");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 24: {
                    ribbon.setName("Trail of the Saber Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 25: {
                    ribbon.setName("Special Service Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 26: {
                    ribbon.setName("Continuous Learning Skills Training Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 27: {
                    ribbon.setName("Continuous Learning Training Workshop Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 28: {
                    ribbon.setName("Highest Advancement Ribbon earned as a Ranger");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 29: {
                    ribbon.setName("Junior Leaders Service Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 30: {
                    ribbon.setName("Expedition Rangers Achievement Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 31: {
                    ribbon.setName("Historical Gold Medal of Achievement Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 32: {
                    ribbon.setName("Adventure Rangers Achievement Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 33: {
                    ribbon.setName("Discovery Rangers Achievement Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 34: {
                    ribbon.setName("Ranger Kids Achievement Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 35: {
                    ribbon.setName("Sky Blue Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 36: {
                    ribbon.setName("Platinum Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 37: {
                    ribbon.setName("Yellow Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 38: {
                    ribbon.setName("Green Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 39: {
                    ribbon.setName("Brown Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 40: {
                    ribbon.setName("Red Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 41: {
                    ribbon.setName("Blue Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
                case 42: {
                    ribbon.setName("Orange Merit Ribbon");
                    ribbon.setDescription(" ");
                    ribbon.setLogoUrl(null);
                    ribbon.setRewardMark(RewardMark.DEFAULT);
                    ribbon.setRewardType(RewardType.LATH);
                    break;
                }
            }
            rewards.add(ribbon);
        });
        return rewards;
    }
}
