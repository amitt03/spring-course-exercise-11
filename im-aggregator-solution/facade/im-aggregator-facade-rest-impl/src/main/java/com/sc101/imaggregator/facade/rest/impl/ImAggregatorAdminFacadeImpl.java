package com.sc101.imaggregator.facade.rest.impl;

import com.sc101.imaggregator.facade.rest.api.ImAggregatorAdminFacade;
import com.sc101.imaggregator.facade.rest.api.ImAggregatorFacade;
import com.sc101.imaggregator.facade.rest.api.dto.ImMessageDTO;
import com.sc101.imaggregator.service.api.ImAggregator;
import com.sc101.imaggregator.service.model.ImMessage;
import com.sc101.imaggregator.service.model.ImType;
import com.sc101.imaggregator.service.model.Participant;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
@RestController
@RequestMapping("/admin")
@Api(basePath = "/admin", value = "/admin", description = "IM Aggregator Admin")
public class ImAggregatorAdminFacadeImpl implements ImAggregatorAdminFacade {

    private ImAggregator imAggregator;

    @Autowired
    public void setImAggregator(ImAggregator imAggregator) {
        this.imAggregator = imAggregator;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get Profit", httpMethod = "GET", response = Long.class, position = 0)
    public long getProfit() {
        long profit = this.imAggregator.getProfit();
        return profit;
    }
}
