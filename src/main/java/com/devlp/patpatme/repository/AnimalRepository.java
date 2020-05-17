package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {
    AnimalEntity findOneById(Integer id);
    AnimalEntity findOneByName(String name);
}
