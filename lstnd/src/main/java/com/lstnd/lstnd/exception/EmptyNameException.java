package com.lstnd.lstnd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyNameException extends Exception {

    public EmptyNameException(String message) {
        super(message);
    }

}
