package com.royalrangers.dto.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CityDto {
    private Date createDate;
    private Date updateDate;
    private String name;
    Long countryId;
}
