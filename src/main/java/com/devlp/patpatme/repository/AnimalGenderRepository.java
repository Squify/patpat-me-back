package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.AnimalGenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimalGenderRepository extends JpaRepository<AnimalGenderEntity, Integer> {
    AnimalGenderEntity findOneByName(String name);
}
