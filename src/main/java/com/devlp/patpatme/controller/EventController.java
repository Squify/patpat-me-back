package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.entity.EventTypeEntity;
import com.devlp.patpatme.repository.EventTypeRepository;
import com.devlp.patpatme.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private EventService eventService;

    //    @ApiOperation(value = "Créer un nouvel évènement dans la base de données")
    @PostMapping(value = "/api/event/create")
    public ResponseEntity createEvent(@RequestBody CreateEventDto createEventDto) {

//        // check the inputs
//        if (!UserUtil.checkCreatePersonInputsAreValid(createAccountDto))
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//
//        // check if mail is already used
//        if (userService.userExistsWithMail(createAccountDto.getMail()))
//            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
//
//        // check if pseudo is already used
//        if (userService.userExistsWithPseudo(createAccountDto.getPseudo()))
//            return new ResponseEntity(HttpStatus.CONFLICT);

        try {

            eventService.createEvent(createEventDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/event/type")
    public List<EventTypeEntity> getEventType() {
        return eventTypeRepository.findAll();
    }
}
