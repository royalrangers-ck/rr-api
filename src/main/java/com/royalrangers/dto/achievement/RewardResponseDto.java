package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.RewardMark;
import com.royalrangers.enums.achivement.RewardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardResponseDto {
    private Long id;
    private Long userId;
    private UserAchievementDto user;
    private Long rewardId;
    private String rewardName;
    private String rewardDescription;
    private String rewardLogoUrl;
    private RewardType rewardType;
    private RewardMark rewardMark;
}
