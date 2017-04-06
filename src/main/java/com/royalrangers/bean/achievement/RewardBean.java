package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.RewardMark;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.achievement.Reward;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private Long rewardId;
    private String rewardName;
    private String rewardDescription;
    private String rewardLogoUrl;
    private RewardType rewardType;
    private RewardMark rewardMark;
}
