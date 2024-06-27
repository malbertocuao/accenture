package com.accenture.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExists extends RuntimeException  {

    public ResourceAlreadyExists(String message) {
        super(message);
    }

}
