package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.MetUserDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface MeetService {

    Set<MetUserDTO> getMetUsers(List<EventEntity> lastEvents, UserEntity currentUser);

    void changeFriendRelation(UserEntity currentUser, UserEntity friend);
}
