package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animal")
@Data
@Accessors(chain = true)
public class AnimalEntity {

    @Id
    @SequenceGenerator(name = "animal_generator", sequenceName = "animal_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Timestamp birthday;

    @ManyToOne
    @JoinColumn(name = "fk_id_owner")
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "fk_id_gender")
    private AnimalGenderEntity gender;

    @ManyToOne
    @JoinColumn(name = "fk_id_type")
    private AnimalTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "fk_id_breed", nullable = true)
    private BreedEntity breed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "animal_temper",
            joinColumns = @JoinColumn(name = "fk_id_animal", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "fk_id_temper", referencedColumnName = "id", nullable = true)
    )
    private List<TemperEntity> tempers = new ArrayList<>();

    @Column(name = "image_path")
    private String image_path;
}
