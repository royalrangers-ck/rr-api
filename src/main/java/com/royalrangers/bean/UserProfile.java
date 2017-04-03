package com.royalrangers.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.UserRank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonSerialize
public class UserProfile {
    private Date createDate;
    private Date updateDate;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String telephoneNumber;
    private Long birthDate;
    private UserAgeGroup userAgeGroup;
    private UserRank userRank;
    private Long countryId;
    private Long cityId;
    private Long groupId;
    private Long platoonId;
    private Long sectionId;
    private String avatarUrl;
}
