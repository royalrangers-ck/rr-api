package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.UserAgeGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Section extends BaseModel{

    @JsonView(Views.Profile.class)
    @NotNull
    private String name;

    @JsonView(Views.Public.class)
    private UserAgeGroup userAgeGroup;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "platoon_id")
    private Platoon platoon;

    public Section() {}

    public Section(Platoon platoon, String name) {
        this.platoon = platoon;
        this.name = name;
    }

}
