package com.royalrangers.model.achievements;

import com.royalrangers.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Reward extends Achievement {

    @Enumerated
    private RewardMark rewardMark;

    @Enumerated
    private RewardType rewardType;

}
