package com.sc101.imaggregator.vendor.joker.impl;

import com.sc101.imaggregator.service.model.ImMessage;
import com.sc101.imaggregator.service.model.ImType;
import com.sc101.imaggregator.vendor.api.ImAggregatorVendor;
import com.sc101.imaggregator.vendor.api.ImQuote;
import com.sc101.imaggregator.vendor.api.exception.ImQuoteExpiredException;
import com.sc101.imaggregator.vendor.api.exception.ImTypeNotSupportedException;
import com.sc101.imaggregator.vendor.api.exception.UnrecognizedQuoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class ImAggregatorVendorJokerImpl implements ImAggregatorVendor {

    private Logger logger = LoggerFactory.getLogger(ImAggregatorVendorJokerImpl.class);

    private Collection<ImType> supportedTypes;
    private String vendorName;
    private int quoteGraceTimeInMinutes;
    private Map<String, ImMessage> quoteMessages;
    private Map<String, ImQuote> quotes;

    public ImAggregatorVendorJokerImpl() {
        this.vendorName = VendorNamesFactory.generateNextVendorName();
        this.supportedTypes = randomlyGenerateSupportedTypes();
        this.quoteGraceTimeInMinutes = randomlyGenerateQuoteGraceTimeInMinutes();
        this.quoteMessages = new ConcurrentHashMap<>();
        this.quotes = new ConcurrentHashMap<>();
        logger.info("Vendor {} supports the following imType: {} AND has quote grace time of {} " +
                    "minutes", this.vendorName, this.supportedTypes, this.quoteGraceTimeInMinutes);
    }

    @Override
    public Collection<ImType> getSupportedImTypes() {
        return this.supportedTypes;
    }

    @Override
    public ImQuote getQuote(ImMessage imMessage) {
        if (!supportedTypes.contains(imMessage.getImType())) {
            throw new ImTypeNotSupportedException("ImType " + imMessage.getImType() + " is not supported by vendor " + this.vendorName);
        }
        int priceInCent = generatePriceInCent();
        Date quoteDueDate = generateQuoteDueDate();
        ImQuote imQuote = new ImQuote(this.vendorName, priceInCent, quoteDueDate);
        this.quotes.put(imQuote.getId(), imQuote);
        this.quoteMessages.put(imQuote.getId(), imMessage);
        logger.debug("Requested quote on imMessage: {}, returning quote: {}", imMessage, imQuote);
        return imQuote;
    }

    @Override
    public int sendImMessage(String imQuoteId) {
        ImQuote imQuote = this.quotes.remove(imQuoteId);
        ImMessage imMessage = this.quoteMessages.remove(imQuoteId);
        if (imQuote == null || imMessage == null) {
            throw new UnrecognizedQuoteException("Unrecognized quote " + imQuoteId);
        }
        Date currentTime = new Date();
        if (currentTime.after(imQuote.getExpirationDate())) {
            throw new ImQuoteExpiredException("Quote expired at " + imQuote.getExpirationDate());
        }
        logger.info("Sent imMessage: {} at the price of {} cents", imMessage, imQuote.getPriceInCent());
        return imQuote.getPriceInCent();
    }

    private Collection<ImType> randomlyGenerateSupportedTypes() {
        Random random = new Random();
        ImType[] imTypes = ImType.values();
        int numOfSupportedImTypes = random.nextInt(imTypes.length);
        Collection<ImType> types = new ArrayList<>(numOfSupportedImTypes);
        for (int i=0; i<numOfSupportedImTypes; i++) {
            types.add(imTypes[i]);
        }
        return types;
    }

    private int randomlyGenerateQuoteGraceTimeInMinutes() {
        Random random = new Random();
        int randomMinutes = random.nextInt(4);
        return randomMinutes + 1;
    }

    private Date generateQuoteDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, this.quoteGraceTimeInMinutes);
        return calendar.getTime();
    }

    private int generatePriceInCent() {
        Random random = new Random();
        int randomCents = random.nextInt(500);
        return randomCents;
    }

    public Collection<ImType> getSupportedTypes() {
        return supportedTypes;
    }

    public void setSupportedTypes(Collection<ImType> supportedTypes) {
        this.supportedTypes = supportedTypes;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getQuoteGraceTimeInMinutes() {
        return quoteGraceTimeInMinutes;
    }

    public void setQuoteGraceTimeInMinutes(int quoteGraceTimeInMinutes) {
        this.quoteGraceTimeInMinutes = quoteGraceTimeInMinutes;
    }
}
