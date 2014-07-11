package com.sc101.imaggregator.test.system;

import com.sc101.imaggregator.test.system.client.ImAggregatorFacadeClient;
import com.sc101.imaggregator.test.system.dto.ImMessageDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

/**
 * @author Amit Tal
 * @since 5/8/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ImAggregatorFacadeTest {

    private Logger logger = LoggerFactory.getLogger(ImAggregatorFacadeTest.class);

    @Configuration
    @PropertySource("classpath:test.properties")
    static class MyConfig {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }

    @Value("${server.base.url}")
    private String serverBaseUrl;

    private ImAggregatorFacadeClient imAggregatorFacadeClient;

    @Before
    public void setup() {
        imAggregatorFacadeClient = new ImAggregatorFacadeClient(serverBaseUrl);
    }

    @Test
    public void testHappyFlow() {
        ImMessageDTO imMessageDTO = generateImMesageDTO(501);
        long initialProfit = imAggregatorFacadeClient.getProfit();
        imAggregatorFacadeClient.sendSMS(imMessageDTO);
        long currentProfit = imAggregatorFacadeClient.getProfit();
        Assert.assertTrue("Profit should have increased", currentProfit - initialProfit > 0);
    }

    @Test
    public void testThrottling() {
        try {
            for (int i = 0; i < 20; i++) {
                ImMessageDTO imMessageDTO = generateImMesageDTO(500);
                logger.debug("Throttling check, sending message {}", (i + 1));
                imAggregatorFacadeClient.sendSMS(imMessageDTO);
            }
            Assert.fail("Throttling was expected to throw an exception");
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals("Throttling expected TOO MANY REQUESTS", HttpStatus.TOO_MANY_REQUESTS, ex.getStatusCode());
        }
    }

    private ImMessageDTO generateImMesageDTO(int bidInCents) {
        ImMessageDTO imMessageDTO = new ImMessageDTO();
        imMessageDTO.setUserId("1");
        imMessageDTO.setFromNumber("0555555555");
        imMessageDTO.setToNumber("0544444444");
        imMessageDTO.setData("Hello World");
        imMessageDTO.setBidInCent(bidInCents);
        return imMessageDTO;
    }
}
