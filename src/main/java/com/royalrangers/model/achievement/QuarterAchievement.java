package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class QuarterAchievement extends Achievement{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quarterAchievement")
    private List<Test> test;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yearAchievement_id")
    private YearAchievement yearAchievement;

}