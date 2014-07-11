package com.sc101.imaggregator.facade.rest.impl.exception;

import com.sc101.imaggregator.facade.rest.api.dto.ErrorDTO;
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

    // TODO Parse error and create an error DTO to fit your project concerns
    // TODO Parse error and create an error DTO to fit your project concerns
    // TODO Parse error and create an error DTO to fit your project concerns


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("Caught validation exception", ex);
        // TODO Can iterate on FieldError and add all errors to message
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

    // TODO Add other cases
}
