package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "animal_type")
@Data
@Accessors(chain = true)
public class AnimalTypeEntity {

    @Id
    @SequenceGenerator(name = "animal_type_generator", sequenceName = "animal_type_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_type_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
