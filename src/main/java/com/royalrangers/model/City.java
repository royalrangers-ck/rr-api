package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class City {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Group> groups;

    public City() {}
}
