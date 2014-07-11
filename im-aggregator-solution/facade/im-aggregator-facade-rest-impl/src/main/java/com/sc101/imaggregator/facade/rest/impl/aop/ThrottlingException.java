package com.sc101.imaggregator.facade.rest.impl.aop;

/**
 * @author Amit Tal
 * @since 5/7/2014
 */
public class ThrottlingException extends RuntimeException {
    public ThrottlingException(String message) {
        super(message);
    }
}
