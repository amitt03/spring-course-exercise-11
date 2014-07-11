package com.sc101.imaggregator.service.impl;

import com.sc101.imaggregator.service.api.ImAggregator;
import com.sc101.imaggregator.service.api.InsufficientBidException;
import com.sc101.imaggregator.service.model.ImMessage;
import com.sc101.imaggregator.service.model.ImType;
import com.sc101.imaggregator.vendor.api.ImAggregatorVendor;
import com.sc101.imaggregator.vendor.api.ImQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
@Service
public class ImAggregatorImpl implements ImAggregator {

    private Logger logger = LoggerFactory.getLogger(ImAggregatorImpl.class);

    private ImAggregatorVendor[] vendors;
    private long totalProfit;

    @Autowired
    public void setVendors(ImAggregatorVendor[] vendors) {
        this.vendors = vendors;
    }

    @Override
    public void sendMessage(ImMessage imMessage, int bidInCent) {
        logger.debug("Request to send message {} with bid of {} cents", imMessage, bidInCent);
        // Get cheapest supporting quote
        ImAggregatorVendor cheapestQuoteVendor = null;
        ImQuote cheapestQuote = null;
        for (ImAggregatorVendor vendor : vendors) {
            Collection<ImType> supportedImTypes = vendor.getSupportedImTypes();
            if (supportedImTypes.contains(imMessage.getImType())) {
                ImQuote quote = vendor.getQuote(imMessage);
                logger.trace("Got quote: {}", quote);
                if (cheapestQuote == null || cheapestQuote.getPriceInCent() > quote.getPriceInCent()) {
                    cheapestQuote = quote;
                    cheapestQuoteVendor = vendor;
                }
            }
        }

        // Check if we can make a profit
        if (cheapestQuote != null && cheapestQuote.getPriceInCent() <= bidInCent) {
            int priceInCent = cheapestQuoteVendor.sendImMessage(cheapestQuote.getId());
            int profit = bidInCent - priceInCent;
            this.totalProfit += profit;
            logger.debug("Made a profit of {} cents, current total profit is {} cents", profit, this.totalProfit);
        } else {
            logger.error("Cannot make a profit out of this message! Bid is {} cents and cheapest quote is {}",
                         bidInCent, (cheapestQuote == null) ? 0 : cheapestQuote.getPriceInCent());
            throw new InsufficientBidException("Bid " + bidInCent + " is not sufficient for sending this message: " + imMessage);
        }
    }

    @Override
    public long getProfit() {
        return this.totalProfit;
    }
}
