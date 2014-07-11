package com.sc101.imaggregator.facade.rest.impl.exception;

import com.sc101.imaggregator.facade.rest.api.dto.ErrorDTO;
import com.sc101.imaggregator.facade.rest.impl.aop.ThrottlingException;
import com.sc101.imaggregator.service.api.InsufficientBidException;
import com.sc101.imaggregator.vendor.api.exception.ImQuoteExpiredException;
import com.sc101.imaggregator.vendor.api.exception.ImTypeNotSupportedException;
import com.sc101.imaggregator.vendor.api.exception.UnrecognizedQuoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Centralized class for handling exceptions.
 * All exception types are caught and transformed to an Error structure and a relevant response code is returned.
 *
 * Notice:
 * The following exception handling is only an example and needs to be enhanced with additional exceptions.
 * Also the returned ErrorDTO is a simple structure that needs to be adjusted to your project needs.
 */
@ControllerAdvice
public class ExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @ExceptionHandler({InsufficientBidException.class, ImQuoteExpiredException.class,
                       ImTypeNotSupportedException.class, UnrecognizedQuoteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleRuntimeException(RuntimeException ex) {
        logger.error("Caught runtime exception", ex);
        ErrorDTO errorDTO = new ErrorDTO("0003", ex.getMessage());
        return errorDTO;
    }

    @ExceptionHandler(ThrottlingException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ResponseBody
    public ErrorDTO handleRuntimeException(ThrottlingException ex) {
        logger.error("Caught throttling exception", ex);
        ErrorDTO errorDTO = new ErrorDTO("0002", ex.getMessage());
        return errorDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("Caught validation exception", ex);
        ErrorDTO errorDTO = new ErrorDTO("0001", ex.getMessage());
        return errorDTO;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO execptionHandler(Exception ex) {
        logger.error("Caught unhandled exception", ex);
        ErrorDTO errorDTO = new ErrorDTO("0000", ex.getMessage());
        return errorDTO;
    }
}
