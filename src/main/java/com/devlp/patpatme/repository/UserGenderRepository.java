package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.UserGenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGenderRepository extends JpaRepository<UserGenderEntity, Integer> {
    Optional<UserGenderEntity> findById(Integer id);
}
