package com.devlp.patpatme.dto.event;

import com.devlp.patpatme.dto.user.EventMemberDTO;
import com.devlp.patpatme.entity.EventTypeEntity;
import com.devlp.patpatme.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EventDTO {

    private Integer id;
    private String name;
    private String description;
    private String location;
    private Timestamp date;
    private EventTypeEntity type;
    private EventMemberDTO owner;
    private List<EventMemberDTO> members = new ArrayList<>();
}
