package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.EventTypeEntity;
import com.devlp.patpatme.repository.EventRepository;
import com.devlp.patpatme.repository.EventTypeRepository;
import com.devlp.patpatme.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public void createEvent(CreateEventDto createEventDto) {

        EventEntity newEvent = new EventEntity();

        newEvent.setName(createEventDto.getName());
        newEvent.setDescription(createEventDto.getDescription());
        newEvent.setLocalisation(createEventDto.getLocalisation());

        if (!createEventDto.getDate().isEmpty()) {
            ZonedDateTime dateZonedDateTime = ZonedDateTime.parse(createEventDto.getDate());
            Timestamp date = Timestamp.from(dateZonedDateTime.toInstant());
            newEvent.setDate(date);
        }


        if (!createEventDto.getFk_id_type().isEmpty()) {
            EventTypeEntity type = eventTypeRepository.findOneByName(createEventDto.getFk_id_type());
            if (type != null)
                newEvent.setType(type);
        }

        eventRepository.save(newEvent);

    }

    @Override
    public boolean eventExistsWithName(String name) {
        return eventRepository.existsEventEntityByNameIgnoreCase(name);
    }
}