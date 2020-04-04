package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    boolean existsEventEntityByNameIgnoreCase(String name);
}
