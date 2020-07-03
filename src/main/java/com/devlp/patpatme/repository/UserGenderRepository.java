package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.UserGenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGenderRepository extends JpaRepository<UserGenderEntity, Integer> {

    UserGenderEntity findOneByName(String name);
}
