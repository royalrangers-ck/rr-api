package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @JsonView(Views.Profile.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createDate;

    private Date updateDate;
    
    public BaseModel() {
        this.createDate = new Date();
        this.updateDate = createDate;
    }
}