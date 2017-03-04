package com.royalrangers.model.achievements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class YearAchievement extends Achievement {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "yearAchievement")
    private List<QuarterAchievement> quarterAchievement;

    @ManyToOne
    @JoinColumn(name = "threeYearAchievement_id")
    private ThreeYearAchievement threeYearAchievement;

}
