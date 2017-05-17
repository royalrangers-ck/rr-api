package com.royalrangers.dto.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CityDto {
    private Long id;
    private String name;
    private Long regionId;
    private String history;
    private String address;
}