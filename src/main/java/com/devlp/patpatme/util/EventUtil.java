package com.devlp.patpatme.util;

import com.devlp.patpatme.dto.event.CreateEventDTO;
import org.apache.commons.lang3.StringUtils;

public class EventUtil {

    static final int MIN_DESCRIPTION_SIZE = 8;
    static final int MAX_DESCRIPTION = 32;

    private EventUtil() {
    }

    public static boolean checkCreateEventInputsAreValid(CreateEventDTO createEventDto) {
        final String name = createEventDto.getName();
        final String description = createEventDto.getDescription();
        final String localisation = createEventDto.getLocalisation();
        final String date = createEventDto.getDate();
        final String type = createEventDto.getType();


        if (StringUtils.isBlank(name) || StringUtils.isBlank(description) || StringUtils.isBlank(localisation) || StringUtils.isBlank(date) || StringUtils.isBlank(type)) {
            return false;
        }

        if (description.length() > MAX_DESCRIPTION) {
            return false;
        }

        return !(description.length() < MIN_DESCRIPTION_SIZE);
    }
}
