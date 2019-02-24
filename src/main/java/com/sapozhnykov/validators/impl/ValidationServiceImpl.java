package com.sapozhnykov.validators.impl;

import com.sapozhnykov.exceptions.BusinessException;
import com.sapozhnykov.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    private static String regexEmail = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static String regexPhone = "(067|097|050)[0-9]{7}";

    @Override
    public void validateEmail(String email) throws BusinessException {
        boolean result = email.matches(regexEmail);
        if(!result) {
            throw new BusinessException("Incorrect format of email!");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        boolean result = phone.matches(regexPhone);
        if(!result) {
            throw new BusinessException("Incorrect format of phone number!");
        }
    }
}
