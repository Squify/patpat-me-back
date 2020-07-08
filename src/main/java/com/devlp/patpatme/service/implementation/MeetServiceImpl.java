package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.user.MetUserDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.mapper.UserMapper;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MeetServiceImpl implements MeetService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<MetUserDTO> getMetUsers(List<EventEntity> lastEvents, UserEntity currentUser) {

        Set<MetUserDTO> metUserDTOS = new HashSet<>();

        for (EventEntity event : lastEvents) {

            if (!event.getOwner().equals(currentUser)) {
                if (metUserDTOS.stream().noneMatch(o -> o.getId().equals(event.getOwner().getId()))) {
                    
                    MetUserDTO metUserToAdd = UserMapper.toMetUserDTO(event.getOwner());
                    metUserToAdd.getEvents().add(event.getName());

                    metUserDTOS.add(metUserToAdd);

                } else {
                    metUserDTOS.stream().filter(o -> o.getId().equals(event.getOwner().getId())).forEach(
                            o -> o.getEvents().add(event.getName())
                    );
                }
            }

            for (UserEntity member : event.getMembers()) {

                if (!member.equals(currentUser)) {
                    if (metUserDTOS.stream().noneMatch(o -> o.getId().equals(member.getId()))) {

                        MetUserDTO metUserToAdd = UserMapper.toMetUserDTO(member);
                        metUserToAdd.getEvents().add(event.getName());

                        metUserDTOS.add(metUserToAdd);
                    } else {
                        metUserDTOS.stream().filter(o -> o.getId().equals(member.getId())).forEach(
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
