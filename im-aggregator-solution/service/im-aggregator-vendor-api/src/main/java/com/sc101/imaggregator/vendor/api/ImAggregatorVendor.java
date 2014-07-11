package com.sc101.imaggregator.vendor.api;

import com.sc101.imaggregator.service.model.ImMessage;
import com.sc101.imaggregator.service.model.ImType;

import java.util.Collection;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public interface ImAggregatorVendor {

    /**
     * @return A collection of supported IM Types
     */
    Collection<ImType> getSupportedImTypes();

    /**
     * Returns a quote for sending an IM Message.
     * Notice that
     *  - The quote price is in cents.
     *  - The quote has an expiration date.
     *
     * @param imMessage IM Message
     * @return IM Quote
     * @throws com.sc101.imaggregator.vendor.api.exception.ImTypeNotSupportedException if IM Type not supported by this vendor
     */
    ImQuote getQuote(ImMessage imMessage);

    /**
     * Send message attached to the quote id.
     *
     * @param imQuoteId Quote id
     * @return price in cents
     * @throws com.sc101.imaggregator.vendor.api.exception.UnrecognizedQuoteException if quote id not recognized
     * @throws com.sc101.imaggregator.vendor.api.exception.ImQuoteExpiredException if quote expiration date has passed
     */
    int sendImMessage(String imQuoteId);

    /**
     * @return Vendor name
     */
    String getVendorName();
}
