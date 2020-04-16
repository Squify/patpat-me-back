package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.AnimalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepository extends JpaRepository<AnimalTypeEntity, Integer> {

    AnimalTypeEntity findOneByName(String name);
}
