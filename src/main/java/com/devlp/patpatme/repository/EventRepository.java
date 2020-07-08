package com.devlp.patpatme.repository;

import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    boolean existsEventEntityByNameIgnoreCase(String name);

    boolean existsEventEntityByOwnerAndId(UserEntity name, Integer eventId);

    EventEntity findOneById(Integer id);

    List<EventEntity> findAllByDateAfter(Timestamp now);

    List<EventEntity> findAllByMembersAndDateAfter(UserEntity user, Timestamp minDate);
}
