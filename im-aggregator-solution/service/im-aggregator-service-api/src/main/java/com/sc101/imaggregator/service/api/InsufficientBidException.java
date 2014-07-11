package com.sc101.imaggregator.service.api;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class InsufficientBidException extends RuntimeException {

    public InsufficientBidException(String message) {
        super(message);
    }
}
