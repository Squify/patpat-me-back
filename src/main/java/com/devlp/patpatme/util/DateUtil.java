package com.devlp.patpatme.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private DateUtil() {
    }

    public static String convertTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toInstant().atOffset(ZoneOffset.UTC).toString();
    }

    public static boolean checkStringDateIsCorrect(String date) {

        try {
            return Instant.parse(date) != null;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
