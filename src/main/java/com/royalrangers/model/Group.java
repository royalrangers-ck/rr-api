package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"group\"")
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Platoon> platoons;

    @Column(nullable = false)
    private String name;

    public Group() {
    }

    public Group(City city, String name) {
        this.city = city;
        this.name = name;
    }
}
