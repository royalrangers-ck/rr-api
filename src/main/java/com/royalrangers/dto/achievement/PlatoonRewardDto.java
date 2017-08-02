package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.RewardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoonRewardDto {
    private Long rewardId;
    private RewardType rewardType;
    private int count;
}
