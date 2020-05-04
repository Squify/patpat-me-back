package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.TemperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperRepository extends JpaRepository<TemperEntity, Integer> {
    TemperEntity findOneByName(String name);
}
