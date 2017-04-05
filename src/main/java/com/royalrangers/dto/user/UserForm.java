package com.royalrangers.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserForm {

    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @NotNull
    @Size(min = 4, max = 100)
    private String comfirmpassword;

}

