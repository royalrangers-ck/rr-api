package com.royalrangers.registration.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserBean {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private Date lastPasswordResetDate;
    private String gender;
    private String status;
    private String country;
    private String city;
    private String group;
    private String platoon;
    private String section;

    public UserBean() {
    }
}

