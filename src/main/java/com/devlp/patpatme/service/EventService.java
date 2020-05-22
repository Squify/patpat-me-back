package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.event.CreateEventDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;

public interface EventService {

    void createEvent(UserEntity user, CreateEventDTO createEventDto);

    EventEntity getEventById(Integer eventId);

    boolean eventExistsWithName(String name);

    boolean checkIfUserIsOwner(UserEntity user, EventEntity event);

    void changeParticipation(UserEntity user, EventEntity event);
}
