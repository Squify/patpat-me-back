package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.BreedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<BreedEntity, Integer> {

    BreedEntity findOneByName(String name);

    List<BreedEntity> findAll();
}
