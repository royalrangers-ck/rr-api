package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class Test extends BaseModel {

    private String name;

    private String shortDescription;

    private String description;

    private String logoUrl;

    @Enumerated
    private TestType testType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private List<Task> taskList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarterAchievement_id", nullable = true)
    private QuarterAchievement quarterAchievement;

}