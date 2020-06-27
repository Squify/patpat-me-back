package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.user.MetUserDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;
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

            for (UserEntity member : event.getMembers()) {

                if (!member.equals(currentUser)) {
                    if (metUserDTOS.stream().noneMatch(o -> o.getId().equals(member.getId()))) {
                        MetUserDTO metUserToAdd = new MetUserDTO();
                        metUserToAdd
                                .setId(member.getId())
                                .setEmail(member.getEmail())
                                .setPseudo(member.getPseudo())
                                .setProfile_pic_path(member.getProfile_pic_path())
                                .setFirstname(member.getFirstname())
                                .setLastname(member.getLastname())
                                .setPhone(member.getPhone() != null ? member.getPhone() : "")
                                .setDisplay_email(member.isDisplay_email())
                                .setDisplay_phone(member.isDisplay_phone())
                                .setDisplay_real_name(member.isDisplay_real_name())
                                .setGender(member.getGender())
                        ;
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
