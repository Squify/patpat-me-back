package com.devlp.patpatme.util;

import com.devlp.patpatme.dto.event.EventCreateDTO;
import com.devlp.patpatme.dto.event.EventEditDTO;
import org.apache.commons.lang3.StringUtils;

public class EventUtil {

    static final int MIN_DESCRIPTION_SIZE = 8;
    static final int MAX_DESCRIPTION = 400;

    private EventUtil() {
    }

    public static boolean checkCreateEventInputsAreValid(EventCreateDTO eventCreateDto) {
        final String name = eventCreateDto.getName();
        final String description = eventCreateDto.getDescription();
        final String localisation = eventCreateDto.getLocation();
        final String date = eventCreateDto.getDate();
        final String type = eventCreateDto.getType();


        if (StringUtils.isBlank(name) || StringUtils.isBlank(description) || StringUtils.isBlank(localisation) || StringUtils.isBlank(date) || StringUtils.isBlank(type)) {
            return false;
        }

        if (description.length() > MAX_DESCRIPTION) {
            return false;
        }

        return !(description.length() < MIN_DESCRIPTION_SIZE);
    }

    public static boolean checkEditEventInputsAreValid(EventEditDTO eventEditDTO) {
        final String description = eventEditDTO.getDescription();
        final String localisation = eventEditDTO.getLocation();
        final String date = eventEditDTO.getDate();


        if (StringUtils.isBlank(description) || StringUtils.isBlank(localisation) || StringUtils.isBlank(date)) {
            return false;
        }

        if (description.length() > MAX_DESCRIPTION) {
            return false;
        }

        return !(description.length() < MIN_DESCRIPTION_SIZE);
    }
}
