package com.royalrangers.model.achievements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class QuarterAchievement extends Achievement{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "quarterAchievement")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "yearAchievement_id")
    private YearAchievement yearAchievement;

}
