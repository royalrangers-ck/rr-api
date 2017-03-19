package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Platoon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "platoon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Section> sections;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public Platoon() {}

    public Platoon(Group group, String name) {
        this.group = group;
        this.name = name;
    }

}
