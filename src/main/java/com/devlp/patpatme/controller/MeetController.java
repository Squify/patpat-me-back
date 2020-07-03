package com.devlp.patpatme.controller;

import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.repository.EventRepository;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.MeetService;
import com.devlp.patpatme.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class MeetController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeetService meetService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Récupère tous les utilisateurs rencontrés lors d'évenements")
    @GetMapping(value = "/api/meet/users")
    public Object getAllMetUsers(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());

        Timestamp minDate = Timestamp.from(Instant.now().minus(30, ChronoUnit.DAYS));
        List<EventEntity> events = eventRepository.findAllByMembersAndDateAfter(userEntity, minDate);

        try {
            return meetService.getMetUsers(events, userEntity);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Récupère tous les amis ajoutés")
    @PostMapping(value = "/api/meet/relation")
    public ResponseEntity changeFriendRelation(CurrentUser user, @RequestBody Integer userId) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        UserEntity friend = userRepository.getUserById(userId);

        // check if user exist
        if (friend == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST); //400

        try {
            meetService.changeFriendRelation(userEntity, friend);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
