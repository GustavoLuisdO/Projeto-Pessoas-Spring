package com.ot.pessoa.dao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyExceptions extends RuntimeException{

    public MyExceptions(String msg) {
        super(msg);
    }
}