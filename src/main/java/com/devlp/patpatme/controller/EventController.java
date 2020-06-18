package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.event.EventCreateDTO;
import com.devlp.patpatme.dto.event.EventEditDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.EventTypeEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
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

import java.sql.Timestamp;
import java.time.Instant;
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
    public ResponseEntity createEvent(CurrentUser user, @RequestBody EventCreateDTO eventCreateDto) {

        // check the inputs
        if (!EventUtil.checkCreateEventInputsAreValid(eventCreateDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // check if name is already used
        if (eventService.eventExistsWithName(eventCreateDto.getName()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED); //417

        try {

            UserEntity userEntity = userService.loadUserById(user.getId());
            eventService.createEvent(userEntity, eventCreateDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/event/edit")
    public ResponseEntity editEvent(CurrentUser user, @RequestBody EventEditDTO eventEditDTO) {

        // check if connected user is owner
        EventEntity event = eventService.getEventById(eventEditDTO.getId());
        if (!user.getId().equals(event.getOwner().getId()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED); //417

        // check the inputs
        if (!EventUtil.checkEditEventInputsAreValid(eventEditDTO))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        try {
            eventService.editEvent(eventEditDTO);
            return new ResponseEntity(HttpStatus.OK);
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
        return eventRepository.findAllByDateAfter(Timestamp.from(Instant.now()));
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

    @PostMapping(value = "/api/event/participation")
    public ResponseEntity changeEventParticipation(CurrentUser user, @RequestBody Integer eventId) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        EventEntity event = eventRepository.findOneById(eventId);

        // check if event exist
        if (event == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST); //400

        // check if user is not event owner
        if (eventService.checkIfUserIsOwner(userEntity, event))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED); //417

        try {
            eventService.changeParticipation(userEntity, event);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
