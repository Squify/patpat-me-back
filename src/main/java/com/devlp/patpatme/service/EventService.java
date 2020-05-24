package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.event.EventCreateDTO;
import com.devlp.patpatme.dto.event.EventEditDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;

public interface EventService {

    void createEvent(UserEntity user, EventCreateDTO eventCreateDto);

    void editEvent(EventEditDTO eventEditDTO);

    EventEntity getEventById(Integer eventId);

    boolean eventExistsWithName(String name);

    boolean checkIfUserIsOwner(UserEntity user, EventEntity event);

    void changeParticipation(UserEntity user, EventEntity event);
}
