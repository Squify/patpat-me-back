package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceRepository extends JpaRepository<RaceEntity, Integer> {
    RaceEntity findOneByName(String name);
    List<RaceEntity> findAll();

}
