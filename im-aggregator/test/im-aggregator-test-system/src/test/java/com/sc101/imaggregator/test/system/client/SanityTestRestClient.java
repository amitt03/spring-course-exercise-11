package com.sc101.imaggregator.test.system.client;

import com.sc101.imaggregator.test.system.dto.SanityTestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Rest client, based on Spring RestTemplate, for communicating with Sanity Facade via REST protocol
 */
public class SanityTestRestClient {

    private Logger logger = LoggerFactory.getLogger(SanityTestRestClient.class);

    private static final String SANITY_TEST_RESOURCE = "/sanity";

    // URLs
    private String baseUrl;

    // Rest template
    private RestTemplate restTemplate;

    public SanityTestRestClient(String serverBaseUrl) {
        restTemplate = new RestTemplate();
        baseUrl = serverBaseUrl + SANITY_TEST_RESOURCE;
    }

    public SanityTestDTO readSanityTest(String id) {
        final ResponseEntity<SanityTestDTO> responseEntity = restTemplate.getForEntity(baseUrl + "/{id}", SanityTestDTO.class, id);
        return responseEntity.getBody();
    }

    public SanityTestDTO createSanityTest(SanityTestDTO sanityTestDTO) {
        final ResponseEntity<SanityTestDTO> responseEntity = restTemplate.postForEntity(baseUrl, sanityTestDTO, SanityTestDTO.class);
        return responseEntity.getBody();
    }
}
