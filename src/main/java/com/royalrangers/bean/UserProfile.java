package com.royalrangers.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class UserProfile {

    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String telephoneNumber;
    private String birthDate;
    private String country;
    private String city;
    private String group;
    private String platoon;
    private String section;
}
