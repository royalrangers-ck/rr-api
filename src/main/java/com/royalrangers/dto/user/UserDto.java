package com.royalrangers.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends UserProfileDto{

    private Boolean enabled;
    private Boolean confirmed;
    private Boolean approved;
}

