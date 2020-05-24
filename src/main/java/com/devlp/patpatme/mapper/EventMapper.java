package com.devlp.patpatme.mapper;

import com.devlp.patpatme.dto.event.EventCreateDTO;
import com.devlp.patpatme.dto.event.EventDTO;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.mapper.util.ModelMapperUtil;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class EventMapper {

    public static EventDTO toDTO(EventEntity event) {
        return ModelMapperUtil.createModelMapper(EventEntity.class, EventDTO.class, event);
    }

    public static EventEntity toEntity(EventDTO dto) {
        return ModelMapperUtil.createModelMapper(EventDTO.class, EventEntity.class, dto);
    }

    public static EventEntity toEntity(EventCreateDTO dto) {

        EventEntity event = ModelMapperUtil.createModelMapper(EventCreateDTO.class, EventEntity.class, dto, EventEntity::setDate);

        ZonedDateTime date = ZonedDateTime.parse(dto.getDate());
        event.setDate(Timestamp.from(date.toInstant()));

        return event;
    }

}
