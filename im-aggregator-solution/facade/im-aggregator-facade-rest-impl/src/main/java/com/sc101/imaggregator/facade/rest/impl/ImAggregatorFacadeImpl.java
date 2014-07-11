package com.sc101.imaggregator.facade.rest.impl;

import com.sc101.imaggregator.facade.rest.api.ImAggregatorFacade;
import com.sc101.imaggregator.facade.rest.api.dto.ErrorDTO;
import com.sc101.imaggregator.facade.rest.api.dto.ImMessageDTO;
import com.sc101.imaggregator.facade.rest.impl.aop.Throttling;
import com.sc101.imaggregator.service.api.ImAggregator;
import com.sc101.imaggregator.service.model.ImMessage;
import com.sc101.imaggregator.service.model.ImType;
import com.sc101.imaggregator.service.model.Participant;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
@RestController
@RequestMapping("/im")
@Api(basePath = "/im", value = "/im", description = "IM Aggregator Facade")
public class ImAggregatorFacadeImpl implements ImAggregatorFacade {

    private ImAggregator imAggregator;

    @Autowired
    public void setImAggregator(ImAggregator imAggregator) {
        this.imAggregator = imAggregator;
    }

    @Override
    @Throttling
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Send SMS", httpMethod = "POST", response = Void.class, position = 0)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDTO.class),
            @ApiResponse(code = 429, message = "Too many request per minute", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Something went wrong", response = ErrorDTO.class)
    })
    public void sendSMS(@ApiParam
                        @Valid
                        @RequestBody
                        ImMessageDTO imMessageDTO) {
        Participant from = new Participant(imMessageDTO.getFromNumber(), imMessageDTO.getFromNumber());
        Participant to = new Participant(imMessageDTO.getToNumber(), imMessageDTO.getToNumber());
        ImMessage imMessage = new ImMessage(ImType.SMS, imMessageDTO.getData(), from, to);
        this.imAggregator.sendMessage(imMessage, imMessageDTO.getBidInCent());
    }
}
