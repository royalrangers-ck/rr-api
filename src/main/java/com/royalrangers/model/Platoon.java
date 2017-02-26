package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "platoon")
public class Platoon {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "platoon", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Section> sections;

    @Column(nullable = false)
    private String name;

    public Platoon() {
    }

    public Platoon(Group group, String name) {
        this.group = group;
        this.name = name;
    }
}
