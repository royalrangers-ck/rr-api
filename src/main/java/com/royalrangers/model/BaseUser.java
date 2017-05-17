package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.UserRank;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUser extends BaseModel {

    @JsonView(Views.Achievement.class)
    private String firstName;

    @JsonView(Views.Achievement.class)
    private String lastName;

    @JsonView(Views.Profile.class)
    private String gender;

    @JsonView(Views.Profile.class)
    private String telephoneNumber;

    @JsonView(Views.Profile.class)
    private Long birthDate;

    @JsonView(Views.Achievement.class)
    private String email;

    @JsonView(Views.Profile.class)
    private UserAgeGroup userAgeGroup;

    @JsonView(Views.Profile.class)
    private UserRank userRank;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "platoon_id")
    private Platoon platoon;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private Section section;
}
