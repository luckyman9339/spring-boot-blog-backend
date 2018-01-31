package com.shaked.blog.exception;

/**
 * Created by USER1 on 1/30/2018.
 */
public abstract class ServiceException extends RuntimeException{
    public ServiceException() {
    }

    public ServiceException(String s) {
        super(s);
    }
}
