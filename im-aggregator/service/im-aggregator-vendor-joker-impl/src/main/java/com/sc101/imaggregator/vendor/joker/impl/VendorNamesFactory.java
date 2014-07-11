package com.sc101.imaggregator.vendor.joker.impl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class VendorNamesFactory {

    private static AtomicInteger index = new AtomicInteger();

    public static String generateNextVendorName() {
        return "vendor:" + index.incrementAndGet();
    }
}
