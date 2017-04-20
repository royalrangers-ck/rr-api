package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.UserRank;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel {

    @JsonView(Views.AchievementProfile.class)
    private String email;

    private String password;

    @JsonView(Views.AchievementProfile.class)
    private String firstName;

    @JsonView(Views.AchievementProfile.class)
    private String lastName;

    @JsonView(Views.Profile.class)
    private String gender;

    @JsonView(Views.Profile.class)
    private String telephoneNumber;

    @JsonView(Views.Profile.class)
    private Long birthDate;

    private Boolean enabled;
    private Boolean confirmed;
    private Boolean approved;

    @JsonView(Views.Profile.class)
    private UserAgeGroup userAgeGroup;

    @JsonView(Views.Profile.class)
    private UserRank userRank;

    @JsonView(Views.AchievementProfile.class)
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "platoon_id")
    private Platoon platoon;

    @JsonView(Views.Profile.class)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private Section section;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities;

}
