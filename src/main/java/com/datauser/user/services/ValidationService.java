package com.datauser.user.services;

import com.datauser.user.payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final String NAME_IS_MANDATORY = "nameIsMandatory";
    private static final String SURE_NAME_IS_MANDATORY = "surnameIsMandatory";
    private static final String EMAIL_IS_MANDATORY = "emailIsMandatory";
    private static final String EMAIL_IS_NOT_VALID = "emailIsInWrongFormat";
    private static final String REQUEST_IS_NULL = "nullRequest";


    public String validate(UserDto userDto) {
        if (userDto == null) {
            return REQUEST_IS_NULL;
        }
        String errorKey;

        errorKey = checkName(userDto.getName());
        if (!errorKey.equals("")) {
            return errorKey;
        }

        errorKey = checkSureName(userDto.getSurename());
        if (!errorKey.equals("")) {
            return errorKey;
        }

        errorKey = checkEmail(userDto.getEmail());
        if (!errorKey.equals("")) {
            return errorKey;
        }

        return checkEmailValidFormat(userDto.getEmail());
    }

    private String checkName(String name) {
        if (name == null || name.equals("")) {
            return NAME_IS_MANDATORY;
        }
        return "";
    }

    private String checkSureName(String sureName) {
        if (sureName == null || sureName.equals("")) {
            return SURE_NAME_IS_MANDATORY;
        }
        return "";
    }

    private String checkEmail(String email) {
        if (email == null || email.equals("")) {
            return EMAIL_IS_MANDATORY;
        }
        return "";
    }

    private String checkEmailValidFormat(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return EMAIL_IS_NOT_VALID;
        }
        return "";
    }
}
