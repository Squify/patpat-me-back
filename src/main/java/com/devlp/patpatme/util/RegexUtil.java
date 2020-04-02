package com.devlp.patpatme.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private RegexUtil(){}

    private static final Pattern specialCharacters = Pattern.compile("[!@#%^$&*()/\\\\_+\\-=\\[\\]{}'~\"|,.;:<>?éèàêôûç°£µ]+");
    private static final Pattern mailCharacters = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern phoneCharacters = Pattern.compile("(?:(?:\\+|00)([1-9][0-9]{0,2})|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");

    static boolean passwordIsSecure(String password) {

        // No lower case letter
        if (StringUtils.upperCase(password).equals(password))
            return false;

        // No upper case letter
        if (StringUtils.lowerCase(password).equals(password))
            return false;

        // No digits
        if (StringUtils.isAllEmpty(StringUtils.getDigits(password)))
            return false;

        // No special char
        Matcher m = specialCharacters.matcher(password);
        return m.find();
    }

    public static boolean mailIsValid(String mail) {
        Matcher m = mailCharacters.matcher(mail);
        return m.find();
    }

    static boolean phoneIsValid(String phone) {
        Matcher m = phoneCharacters.matcher(phone);
        return m.find();
    }

}
