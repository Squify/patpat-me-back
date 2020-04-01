package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "event_type")
@Data
@Accessors(chain = true)
public class EventTypeEntity {

    @Id
    @SequenceGenerator(name = "event_type_generator", sequenceName = "event_type_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_type_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
