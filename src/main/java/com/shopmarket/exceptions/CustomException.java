package com.shopmarket.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Sa uxaki orinak e, ayn voch mi tex chi ogtagorcvel
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "inch vor patchar")
public class CustomException extends DataAccessException {

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
