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
            }
            rewards.add(ribbon);
        });
        return rewards;
    }
}
