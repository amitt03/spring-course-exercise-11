package com.sc101.imaggregator.facade.rest.api;

import com.sc101.imaggregator.facade.rest.api.dto.ImMessageDTO;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public interface ImAggregatorFacade {

    void sendSMS(ImMessageDTO imMessageDTO);
}
