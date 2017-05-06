package com.royalrangers.dto.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CountryDto {
    private Date createDate;
    private Date updateDate;
    private String name;
}
