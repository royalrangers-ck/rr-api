package com.royalrangers.model.achievement;

import com.royalrangers.enums.achivement.RewardMark;
import com.royalrangers.enums.achivement.RewardType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Reward extends Achievement {

    @Enumerated
    private RewardMark rewardMark;

    @Enumerated
    private RewardType rewardType;

}
