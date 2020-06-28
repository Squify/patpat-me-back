package com.devlp.patpatme.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserEntity {

    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @ToString.Exclude
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @Column(name = "email")
    private String email;

    @ToString.Exclude
    @Column(name = "pseudo")
    private String pseudo;

    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @Column(name = "profile_pic_path")
    private String profile_pic_path;

    @ToString.Exclude
    @Column(name = "firstname")
    private String firstname;

    @ToString.Exclude
    @Column(name = "lastname")
    private String lastname;

    @ToString.Exclude
    @Column(name = "phone")
    private String phone;

    @ToString.Exclude
    @Column(name = "birthday")
    private Timestamp birthday;

    @ToString.Exclude
    @Column(name = "sign_up")
    private Timestamp sign_up;

    @ToString.Exclude
    @Column(name = "display_real_name")
    private boolean display_real_name;

    @ToString.Exclude
    @Column(name = "display_email")
    private boolean display_email;

    @ToString.Exclude
    @Column(name = "display_phone")
    private boolean display_phone;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_id_gender")
    private UserGenderEntity gender;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_id_language")
    private LanguageEntity language;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "friend_relation",
            joinColumns = @JoinColumn(name = "fk_id_user", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_id_friend", referencedColumnName = "id", nullable = false)
    )
    private List<UserEntity> friends = new ArrayList<>();
}
