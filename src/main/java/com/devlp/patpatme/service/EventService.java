package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.dto.event.EventDto;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.security.CurrentUser;

public interface EventService {

    void createEvent(UserEntity user, CreateEventDto createEventDto);

    EventEntity getEventById(Integer eventId);

    boolean eventExistsWithName(String name);

    boolean checkIfUserIsOwner(UserEntity user, EventEntity event);

    void changeParticipation(UserEntity user, EventEntity event);
}
