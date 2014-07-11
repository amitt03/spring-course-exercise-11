package com.sc101.imaggregator.service.api;

import com.sc101.imaggregator.service.model.ImMessage;

import java.util.Collection;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public interface ImAggregator {

    /**
     * Send message
     *
     * @param imMessage IM Message
     * @param bidInCent bid in cents
     */
    void sendMessage(ImMessage imMessage, int bidInCent);

    /**
     * Get total profit
     * @return total profit
     */
    long getProfit();
}
