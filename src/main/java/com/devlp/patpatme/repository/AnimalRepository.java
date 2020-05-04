package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

}
