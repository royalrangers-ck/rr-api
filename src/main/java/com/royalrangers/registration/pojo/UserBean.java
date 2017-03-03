package com.royalrangers.registration.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
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

