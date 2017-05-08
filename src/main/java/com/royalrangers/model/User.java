package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseUser {

    @JsonView(Views.Achievement.class)
    private String email;

    private String password;
    private Boolean enabled;
    private Boolean confirmed;
    private Boolean approved;
    private Boolean rejected;

    @JsonView(Views.Achievement.class)
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @JsonView(Views.Profile.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities;

}
