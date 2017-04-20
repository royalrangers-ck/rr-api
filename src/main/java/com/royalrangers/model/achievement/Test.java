package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.BaseModel;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class Test extends BaseModel {

    @JsonView(Views.AchievementProfile.class)
    private String name;

    @JsonView(Views.AchievementProfile.class)
    private String description;

    @JsonView(Views.AchievementProfile.class)
    private String logoUrl;

    @JsonView(Views.AchievementProfile.class)
    @Enumerated
    private TestType testType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private List<Task> taskList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarterAchievement_id", nullable = true)
    private QuarterAchievement quarterAchievement;

}