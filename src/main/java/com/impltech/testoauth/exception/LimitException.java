package com.impltech.testoauth.exception;

/**
 * Created by dima.
 * Creation date 15.02.19.
 */
public class LimitException extends RuntimeException{
    public LimitException(String message) {
        super(message);
    }
}
