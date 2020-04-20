package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.dto.event.EventDto;

public interface EventService {

    void createEvent(CreateEventDto createEventDto);

    EventDto getEventById(Integer eventId);

    boolean eventExistsWithName(String name);
}
