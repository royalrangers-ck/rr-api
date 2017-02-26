package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Group> groups;

    @Column(nullable = false)
    private String name;

    protected City() {
    }

    public City(String name) {
        this.name = name;
    }
}
