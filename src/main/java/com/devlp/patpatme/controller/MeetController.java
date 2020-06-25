package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.user.MetUserDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.repository.EventRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MeetController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping(value = "/api/meet/users")
    public Object getAllMetUsers(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());

        Timestamp minDate = Timestamp.from(Instant.now().minus(30, ChronoUnit.DAYS));
        List<EventEntity> events = eventRepository.findAllByMembersAndDateAfter(userEntity, minDate);

        try {
            List<MetUserDTO> metUserDTOS = new ArrayList<>();

            for (EventEntity event : events) {
                for (UserEntity member : event.getMembers()) {
                    if (!member.equals(userEntity)) {
                        if (metUserDTOS.stream().noneMatch(o -> o.getUser().equals(member))) {
                            MetUserDTO metUserToAdd = new MetUserDTO();
                            metUserToAdd.setUser(member);
                            metUserToAdd.getEvents().add(event.getName());
                            metUserDTOS.add(metUserToAdd);
                        } else {
                            metUserDTOS.stream().filter(o -> o.getUser().equals(member)).forEach(
                                    o -> o.getEvents().add(event.getName())
                            );
                        }
                    }
                }
            }

            return metUserDTOS;
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
