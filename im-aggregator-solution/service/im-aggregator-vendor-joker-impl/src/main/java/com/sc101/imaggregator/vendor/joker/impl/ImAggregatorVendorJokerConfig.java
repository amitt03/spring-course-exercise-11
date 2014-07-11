package com.sc101.imaggregator.vendor.joker.impl;

import com.sc101.imaggregator.vendor.api.ImAggregatorVendor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
@Configuration
public class ImAggregatorVendorJokerConfig {

    // TODO Change to factory and dynamically create vendors

    @Bean
    public ImAggregatorVendor imAggregator1() {
        return new ImAggregatorVendorJokerImpl();
    }

    @Bean
    public ImAggregatorVendor imAggregator2() {
        return new ImAggregatorVendorJokerImpl();
    }

    @Bean
    public ImAggregatorVendor imAggregator3() {
        return new ImAggregatorVendorJokerImpl();
    }

    @Bean
    public ImAggregatorVendor imAggregator4() {
        return new ImAggregatorVendorJokerImpl();
    }

    @Bean
    public ImAggregatorVendor imAggregator5() {
        return new ImAggregatorVendorJokerImpl();
    }
}
