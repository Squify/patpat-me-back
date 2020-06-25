package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.user.MetUserDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetServiceImpl implements MeetService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MetUserDTO> getMetUsers(List<EventEntity> lastEvents, UserEntity currentUser) {

        List<MetUserDTO> metUserDTOS = new ArrayList<>();
        for (EventEntity event : lastEvents) {
            for (UserEntity member : event.getMembers()) {
                if (!member.equals(currentUser)) {
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
    }

    @Override
    public void changeFriendRelation(UserEntity currentUser, UserEntity friend) {

        boolean friends = currentUser.getFriends().stream().anyMatch(userEntity -> userEntity == friend);

        if (friends)
            currentUser.getFriends().remove(friend);
        else
            currentUser.getFriends().add(friend);

        userRepository.save(currentUser);
    }
}
