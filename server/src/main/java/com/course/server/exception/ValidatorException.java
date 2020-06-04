package com.course.server.exception;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-06-03 22:29
 */
public class ValidatorException extends RuntimeException{

    public ValidatorException(String message) {
        super(message);
    }
}
