package com.royalrangers.model.achievement;

import com.royalrangers.model.BaseModel;
import com.royalrangers.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class UserReward extends UserAchievement {

    @OneToOne
    private User user;

    @OneToOne
    private Reward reward;

}
