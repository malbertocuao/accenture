package com.accenture.customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExists extends RuntimeException  {

    public ResourceAlreadyExists(String resourceName,  String filedName, String filedValue) {
        super(String.format("El recurso %s con el valor %s en el campo %s ya existe",
                resourceName, filedValue, filedName));
    }

}
