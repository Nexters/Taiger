package com.nexters.taiger.common.exception;

/**
 * Created by Baek on 2016-01-28.
 */
public class BadAccessTokenException extends RestException {
    public BadAccessTokenException(String message) {
        super(message);
    }
}
