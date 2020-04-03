package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {

    EventTypeEntity findOneByName(String name);
}
