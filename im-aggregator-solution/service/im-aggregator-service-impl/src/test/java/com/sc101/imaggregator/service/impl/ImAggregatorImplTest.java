package com.sc101.imaggregator.service.impl;

import com.sc101.imaggregator.service.api.InsufficientBidException;
import com.sc101.imaggregator.service.model.ImMessage;
import com.sc101.imaggregator.service.model.ImType;
import com.sc101.imaggregator.service.model.Participant;
import com.sc101.imaggregator.vendor.api.ImAggregatorVendor;
import com.sc101.imaggregator.vendor.api.ImQuote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Amit Tal
 * @since 5/8/2014
 */
public class ImAggregatorImplTest {

    @InjectMocks
    private ImAggregatorImpl uut;

    @Mock
    private ImAggregatorVendor imAggregatorVendor1;

    @Mock
    private ImAggregatorVendor imAggregatorVendor2;

    // Static data
    private Participant from;
    private Participant to;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        uut = new ImAggregatorImpl();
        uut.setVendors(new ImAggregatorVendor[] {imAggregatorVendor1, imAggregatorVendor2});
        // Static test data
        from = new Participant("Amit", "0555555555");
        to = new Participant("Tal", "0544444444");
    }

    @Test
    public void testSendMessageHappyFlow() {
        // Setup a positive answer
        setupVendorMock(imAggregatorVendor1, "1", 10);
        setupVendorMock(imAggregatorVendor2, "2", 20);
        // Call API
        ImMessage imMessage = new ImMessage(ImType.SMS, "hello World", from, to);
        uut.sendMessage(imMessage, 100);
        // Verify calls
        Mockito.verify(imAggregatorVendor1).sendImMessage(Matchers.eq("1"));
        Assert.assertEquals("Expected profit of 90 cents", 90, uut.getProfit());
    }

    @Test(expected = InsufficientBidException.class)
    public void testSendMessageLowBid() {
        // Setup a positive answer
        setupVendorMock(imAggregatorVendor1, "1", 100);
        setupVendorMock(imAggregatorVendor2, "2", 200);
        // Call API
        ImMessage imMessage = new ImMessage(ImType.SMS, "hello World", from, to);
        uut.sendMessage(imMessage, 50);
    }

    @Test(expected = InsufficientBidException.class)
    public void testSendMessageUnsupportedImType() {
        // Setup a positive answer
        setupVendorMock(imAggregatorVendor1, "1", 10);
        setupVendorMock(imAggregatorVendor2, "2", 20);
        // Call API
        ImMessage imMessage = new ImMessage(ImType.MMS, "hello World", from, to);
        uut.sendMessage(imMessage, 10);
    }
    
    private ImQuote setupVendorMock(ImAggregatorVendor imAggregatorVendor, String quoteId, int quoteBidInCent) {
        ImQuote mockQuote = new ImQuote(quoteId, "vendorName", quoteBidInCent, new Date());
        Mockito.when(imAggregatorVendor.getSupportedImTypes()).thenReturn(Arrays.asList(ImType.SMS));
        Mockito.when(imAggregatorVendor.getQuote(Matchers.any(ImMessage.class))).thenReturn(mockQuote);
        Mockito.when(imAggregatorVendor.sendImMessage(Matchers.eq(quoteId))).thenReturn(quoteBidInCent);
        return mockQuote;
    }
}
