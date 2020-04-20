package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
@Entity
@Table(name = "animal_temper")
@Data
@Accessors(chain = true)
public class AnimalTemperEntity {
    @Id
    @SequenceGenerator(name = "animal_temper_generator", sequenceName = "animal_temper_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_temper_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
