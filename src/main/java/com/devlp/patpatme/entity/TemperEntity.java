package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
@Entity
@Table(name = "temper")
@Data
@Accessors(chain = true)
public class TemperEntity {
    @Id
    @SequenceGenerator(name = "temper_generator", sequenceName = "temper_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temper_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
