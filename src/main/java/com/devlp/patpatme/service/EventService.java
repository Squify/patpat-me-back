package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.dto.event.EventDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.security.CurrentUser;

public interface EventService {

    void createEvent(UserEntity user, CreateEventDto createEventDto);

    EventDto getEventById(Integer eventId);

    boolean eventExistsWithName(String name);
}
