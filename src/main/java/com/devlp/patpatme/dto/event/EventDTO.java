package com.devlp.patpatme.dto.event;

import com.devlp.patpatme.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EventDTO {

    private Integer id;
    private String name;
    private String description;
    private String localisation;
    private String date;
    private String fk_id_type;
    private UserEntity fk_id_owner;
}
