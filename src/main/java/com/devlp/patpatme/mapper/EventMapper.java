package com.devlp.patpatme.mapper;

import com.devlp.patpatme.dto.event.CreateEventDto;
import com.devlp.patpatme.dto.event.EventDto;
import com.devlp.patpatme.entity.EventEntity;
import com.devlp.patpatme.mapper.util.ModelMapperUtil;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class EventMapper {

    public static EventDto toDTO(EventEntity event) {
        return ModelMapperUtil.createModelMapper(EventEntity.class, EventDto.class, event);
    }

    public static EventEntity toEntity(EventDto dto) {
        return ModelMapperUtil.createModelMapper(EventDto.class, EventEntity.class, dto);
    }

    public static EventEntity toEntity(CreateEventDto dto) {

        EventEntity event = ModelMapperUtil.createModelMapper(CreateEventDto.class, EventEntity.class, dto, EventEntity::setDate);

        ZonedDateTime date = ZonedDateTime.parse(dto.getDate());
        event.setDate(Timestamp.from(date.toInstant()));

        return event;
    }

}
