package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TempUser extends BaseUser {

    @OneToOne
    private User user;

    @JsonView(Views.Profile.class)
    public Long userId() {
        return user.getId();
    }
}
