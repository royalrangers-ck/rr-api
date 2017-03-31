package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createDate;

    private Date updateDate;
    
    public BaseModel() {
        this.createDate = new Date();
        this.updateDate = new Date();
    }
}