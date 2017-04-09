package com.royalrangers.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateAdminDto extends UserUpdateDto {

    private String firstName;
    private String lastName;
    private String gender;
    private Long birthDate;
}
