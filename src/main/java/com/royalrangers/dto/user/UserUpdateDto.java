package com.royalrangers.dto.user;

import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.UserRank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    private String telephoneNumber;
    private UserAgeGroup userAgeGroup;
    private UserRank userRank;
    private Long countryId;
    private Long cityId;
    private Long groupId;
    private Long platoonId;
    private Long sectionId;
}