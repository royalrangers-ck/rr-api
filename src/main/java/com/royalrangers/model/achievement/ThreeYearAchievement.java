package com.royalrangers.model.achievement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class ThreeYearAchievement extends Achievement{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "threeYearAchievement")
    private List<YearAchievement> yearAchievement;

    @ManyToOne
    @JoinColumn(name = "twelveYearAchievement_id")
    private TwelveYearAchievement twelveYearAchievement;

}
