package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserThreeYearAchievement extends UserAchievement {

    @JsonView(Views.Achievement.class)
    @OneToOne
    private ThreeYearAchievement threeYearAchievement;

}
