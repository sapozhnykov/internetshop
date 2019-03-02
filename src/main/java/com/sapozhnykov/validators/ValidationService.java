package com.sapozhnykov.validators;

import com.sapozhnykov.exceptions.BusinessException;

public interface ValidationService {
    void validateEmail(String  email) throws BusinessException;
    void validatePhone(String  phone) throws BusinessException;
}
