package com.royalrangers.model.achievement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class TwelveYearAchievement extends Achievement {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "twelveYearAchievement")
    private List<ThreeYearAchievement> threeYearAchievements;

}
