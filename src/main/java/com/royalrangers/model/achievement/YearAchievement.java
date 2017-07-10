package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "year")
    private List<Test> additionalTests;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "threeYearAchievement_id")
    private ThreeYearAchievement threeYearAchievement;

}
