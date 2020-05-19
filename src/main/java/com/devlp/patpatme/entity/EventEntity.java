package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
@Data
@Accessors(chain = true)
public class EventEntity {
    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "fk_id_type")
    private EventTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "fk_id_owner")
    private UserEntity owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "event_member",
            joinColumns = @JoinColumn(name = "fk_id_event", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "fk_id_member", referencedColumnName = "id", nullable = true)
    )
    private List<UserEntity> members = new ArrayList<>();
}
