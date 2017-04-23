package com.royalrangers.dto.user;

import com.royalrangers.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Long birthDate;
    private String gender;
    private String telephoneNumber;
    private Long countryId;
    private Long cityId;
    private Long groupId;
    private Long platoonId;
    private Long sectionId;
    private UserStatus status;
}
