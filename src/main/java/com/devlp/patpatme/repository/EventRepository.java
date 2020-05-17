package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    boolean existsEventEntityByNameIgnoreCase(String name);
    EventEntity findOneById(Integer id);
    List<EventEntity> findAllByDateAfter(Timestamp now);
}
