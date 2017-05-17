package com.royalrangers.dto.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GroupDto {
    private String name;
    private Long cityId;
    private String history;
    private String address;
}