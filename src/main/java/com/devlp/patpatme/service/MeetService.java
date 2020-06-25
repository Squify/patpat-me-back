package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.MetUserDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;

import java.util.List;

public interface MeetService {

    List<MetUserDTO> getMetUsers(List<EventEntity> lastEvents, UserEntity currentUser);
    void changeFriendRelation(UserEntity currentUser, UserEntity friend);
}
