package com.devlp.patpatme.util;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import org.apache.commons.lang3.StringUtils;


public class UserUtil {

    static final int MIN_PASSWORD_SIZE = 8;
    static final int MAX_PASSWORD_SIZE = 32;

    private UserUtil() {
    }

    public static boolean checkCreatePersonInputsAreValid(CreateAccountDto createAccountDto) {
        final String email = createAccountDto.getEmail();
        final String password = createAccountDto.getPassword();
        final String lastname = createAccountDto.getLastname();
        final String firstname = createAccountDto.getFirstname();
        final String phone = createAccountDto.getPhone();
        final String birthday = createAccountDto.getBirthday();
        final String pseudo = createAccountDto.getPseudo();


        if (StringUtils.isBlank(email) || StringUtils.isBlank(pseudo) || StringUtils.isBlank(password) || StringUtils.isBlank(lastname) || StringUtils.isBlank(firstname)) {
            return false;
        }

        if (password.length() > MAX_PASSWORD_SIZE) {
            return false;
        }

        if (password.length() < MIN_PASSWORD_SIZE) {
            return false;
        }

        if (!StringUtils.isBlank(phone) && !RegexUtil.phoneIsValid(phone)) {
            return false;
        }

        return RegexUtil.emailIsValid(email);
    }


}
