package com.shaked.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by USER1 on 1/30/2018.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceBadRquestException extends ServiceException {
    public ServiceBadRquestException() {
    }

    public ServiceBadRquestException(String s) {
        super(s);
    }
}
