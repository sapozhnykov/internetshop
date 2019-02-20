package com.sapozhnykov.validators.impl;

import com.sapozhnykov.exceptions.BusinessException;
import com.sapozhnykov.validators.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateEmail(String email) throws BusinessException {
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            throw new BusinessException("Incorrect format of email!");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        String regex = "(067|097|050)[0-9]{7}";
        boolean result = phone.matches(regex);
        if(!result) {
            throw new BusinessException("Incorrect format of phone number!");
        }
    }
}
