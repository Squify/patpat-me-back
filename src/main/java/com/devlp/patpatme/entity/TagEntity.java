package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
@Accessors(chain = true)
public class TagEntity {

    @Id
    @SequenceGenerator(name = "tag_generator", sequenceName = "tag_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
