package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TempUser extends BaseUser {
    @OneToOne
    private User user;
}
