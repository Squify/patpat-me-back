package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.EventTypeEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.repository.EventRepository;
import com.devlp.patpatme.repository.EventTypeRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.EventService;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.EventUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    //    @ApiOperation(value = "Créer un nouvel évènement dans la base de données")
    @PostMapping(value = "/api/event/create")
    public ResponseEntity createEvent(CurrentUser user, @RequestBody CreateEventDto createEventDto) {

        // check the inputs
        if (!EventUtil.checkCreateEventInputsAreValid(createEventDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // check if name is already used
        if (eventService.eventExistsWithName(createEventDto.getName()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED); //417

        try {

            UserEntity userEntity = userService.loadUserById(user.getId());
            eventService.createEvent(userEntity, createEventDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/event/type")
    public List<EventTypeEntity> getEventType() {
        return eventTypeRepository.findAll();
    }

    @GetMapping(value = "/api/events")
    public List<EventEntity> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping(value = "/api/event")
    public Object getEvent(@RequestParam Integer eventId) {

        try {
            return eventService.getEventById(eventId);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
