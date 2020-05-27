package com.devlp.patpatme.util;

import com.devlp.patpatme.dto.animal.AnimalCreateDTO;
import com.devlp.patpatme.dto.animal.AnimalEditDTO;
import org.apache.commons.lang3.StringUtils;

public class AnimalUtil {

    static final int MIN_NAME = 1;
    static final int MAX_NAME = 100;

    private AnimalUtil() {
    }

    public static boolean checkCreateAnimalInputsAreValid(AnimalCreateDTO animalCreateDTO) {
        final String name = animalCreateDTO.getName();
        final String type = animalCreateDTO.getType();
        final String gender = animalCreateDTO.getGender();
        final String birthday = animalCreateDTO.getBirthday();


        if (StringUtils.isBlank(name) || StringUtils.isBlank(type) || StringUtils.isBlank(gender) || StringUtils.isBlank(birthday)) {
            return false;
        }

        if (name.length() > MAX_NAME) {
            return false;
        }

        return !(name.length() < MIN_NAME);
    }

    public static boolean checkEditAnimalInputsAreValid(AnimalEditDTO animalEditDTO) {
        final String type = animalEditDTO.getType();
        final String gender = animalEditDTO.getGender();
        final String birthday = animalEditDTO.getBirthday();


        if (StringUtils.isBlank(type) || StringUtils.isBlank(gender)) {
            return false;
        }

        return StringUtils.isBlank(birthday);
    }
}
