package com.royalrangers.dto.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PlatoonDto {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private String name;
    private Long groupId;
    private String history;
    private String logoUrl;
    private String address;
    private Date meetTime;
    private String city;
}
