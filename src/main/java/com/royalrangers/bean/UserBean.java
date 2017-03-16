package com.royalrangers.bean;

import com.royalrangers.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserBean {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String telephonNumber;
    private Long birthDate;
    private Status status;
    private Long countryID;
    private Long cityID;
    private Long groupID;
    private Long platoonID;
    private Long sectionID;

}

