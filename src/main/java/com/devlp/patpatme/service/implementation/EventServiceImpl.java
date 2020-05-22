package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.EventTypeEntity;
import com.devlp.patpatme.entity.UserEntity;
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
    public void createEvent(UserEntity user, CreateEventDto createEventDto) {

        EventEntity newEvent = new EventEntity();

        newEvent.setName(createEventDto.getName());
        newEvent.setDescription(createEventDto.getDescription());
        newEvent.setLocalisation(createEventDto.getLocalisation());
        newEvent.setOwner(user);

        if (!createEventDto.getDate().isEmpty()) {
            ZonedDateTime dateZonedDateTime = ZonedDateTime.parse(createEventDto.getDate());
            Timestamp date = Timestamp.from(dateZonedDateTime.toInstant());
            newEvent.setDate(date);
        }

        if (!createEventDto.getType().isEmpty()) {
            EventTypeEntity type = eventTypeRepository.findOneByName(createEventDto.getType());
            if (type != null)
                newEvent.setType(type);
        }

        eventRepository.save(newEvent);

    }

    @Override
    public EventEntity getEventById(Integer eventId) {

        return eventRepository.findOneById(eventId);
    }

    @Override
    public boolean eventExistsWithName(String name) {
        return eventRepository.existsEventEntityByNameIgnoreCase(name);
    }

    @Override
    public boolean checkIfUserIsOwner(UserEntity user, EventEntity event) {

        return eventRepository.existsEventEntityByOwnerAndId(user, event.getId());
    }

    @Override
    public void changeParticipation(UserEntity user, EventEntity event) {

        boolean participate = event.getMembers().stream().anyMatch(userEntity -> userEntity == user);

        if (participate)
            event.getMembers().remove(user);
        else
            event.getMembers().add(user);

        eventRepository.save(event);
    }
}
