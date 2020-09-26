package com.hil.basiccrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}

