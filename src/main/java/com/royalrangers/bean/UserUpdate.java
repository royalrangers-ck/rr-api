package com.royalrangers.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.royalrangers.enums.UserAgeGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class UserUpdate {
    private String firstName;
    private String lastName;
    private String gender;
    private String telephoneNumber;
    private Long birthDate;
    private UserAgeGroup userAgeGroup;
    private Long countryId;
    private Long cityId;
    private Long groupId;
    private Long platoonId;
    private Long sectionId;
}
