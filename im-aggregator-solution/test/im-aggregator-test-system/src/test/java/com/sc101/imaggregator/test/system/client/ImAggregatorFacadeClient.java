package com.sc101.imaggregator.test.system.client;

import com.sc101.imaggregator.test.system.dto.ImMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Amit Tal
 * @since 5/8/2014
 */
public class ImAggregatorFacadeClient {

    private Logger logger = LoggerFactory.getLogger(ImAggregatorFacadeClient.class);

    private static final String IM_RESOURCE = "/im";
    private static final String ADMIN_RESOURCE = "/admin";

    // URLs
    private String baseUrl;

    // Rest template
    private RestTemplate restTemplate;

    public ImAggregatorFacadeClient(String serverBaseUrl) {
        restTemplate = new RestTemplate();
        baseUrl = serverBaseUrl;
    }

    public void sendSMS(ImMessageDTO imMessageDTO) {
        restTemplate.postForObject(baseUrl + IM_RESOURCE, imMessageDTO, Void.class);
    }

    public long getProfit() {
        Long response = restTemplate.getForObject(baseUrl + ADMIN_RESOURCE, Long.class);
        return response;
    }
}
