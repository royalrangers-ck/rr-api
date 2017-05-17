package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.BaseModel;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
public abstract class Achievement extends BaseModel {

    @JsonView(Views.Achievement.class)
    @NotNull
    private String name;

    @JsonView(Views.Achievement.class)
    @NotNull
    @Column(length = 1200)
    private String description;

    @JsonView(Views.Achievement.class)
    private String logoUrl;

    @JsonView(Views.Achievement.class)
    private UserAgeGroup userAgeGroup;
}
