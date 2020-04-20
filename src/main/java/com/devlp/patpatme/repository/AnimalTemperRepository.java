package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.AnimalTemperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTemperRepository extends JpaRepository<AnimalTemperEntity, Integer> {
    AnimalTemperEntity findOneByName(String name);
}
