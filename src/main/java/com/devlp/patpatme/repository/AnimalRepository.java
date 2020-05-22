package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.AnimalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

    List<AnimalEntity> findAllByOwnerId(Integer id);
}
