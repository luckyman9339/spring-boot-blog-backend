package com.shaked.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by USER1 on 1/30/2018.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ServiceForbiddenException extends ServiceException {
    public ServiceForbiddenException() {
    }

    public ServiceForbiddenException(String s) {
        super(s);
    }
}
