package com.devlp.patpatme.dto.user;

import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FriendDTO {

    private UserEntity user;
    private List<AnimalEntity> animals = new ArrayList<>();
}
