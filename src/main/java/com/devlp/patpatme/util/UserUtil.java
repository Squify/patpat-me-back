package com.devlp.patpatme.util;

import com.devlp.patpatme.dto.user.AccountCreateDTO;
import com.devlp.patpatme.dto.user.AccountEditDTO;
import org.apache.commons.lang3.StringUtils;


public class UserUtil {

    static final int MIN_PASSWORD_SIZE = 8;
    static final int MAX_PASSWORD_SIZE = 32;

    private UserUtil() {
    }

    public static boolean checkCreatePersonInputsAreValid(AccountCreateDTO accountCreateDto) {
        final String email = accountCreateDto.getEmail();
        final String password = accountCreateDto.getPassword();
        final String lastname = accountCreateDto.getLastname();
        final String firstname = accountCreateDto.getFirstname();
        final String phone = accountCreateDto.getPhone();
        final String birthday = accountCreateDto.getBirthday();
        final String pseudo = accountCreateDto.getPseudo();


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

    public static boolean checkEditPersonInputsAreValid(AccountEditDTO accountEditDto) {
        final String email = accountEditDto.getEmail();
        final String password = accountEditDto.getPassword();
        final String phone = accountEditDto.getPhone();

        if (!password.isEmpty() && password.length() > MAX_PASSWORD_SIZE) {
            return false;
        }

        if (!password.isEmpty() && password.length() < MIN_PASSWORD_SIZE) {
            return false;
        }

        if (!StringUtils.isBlank(phone) && !RegexUtil.phoneIsValid(phone)) {
            return false;
        }

        return RegexUtil.emailIsValid(email);
    }


}
