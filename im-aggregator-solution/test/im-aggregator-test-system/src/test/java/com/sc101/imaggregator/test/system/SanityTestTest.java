package com.sc101.imaggregator.test.system;

import com.sc101.imaggregator.test.system.client.SanityTestRestClient;
import com.sc101.imaggregator.test.system.dto.SanityTestDTO;
import com.sc101.imaggregator.test.system.client.SanityTestRestClient;
import com.sc101.imaggregator.test.system.dto.SanityTestDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Sanity System Test
 *
 * Notice:
 * property file test.properties contains the server location (http://host:port/app-name)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SanityTestTest.MyConfig.class, loader = AnnotationConfigContextLoader.class)
public class SanityTestTest {

    private Logger logger = LoggerFactory.getLogger(SanityTestTest.class);

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

    private SanityTestRestClient sanityTestRestClient;

    @Before
    public void setup() {
        sanityTestRestClient = new SanityTestRestClient(serverBaseUrl);
    }

    @Test
    @Ignore
    public void testSanityTestHappyFlow() {
        logger.debug("Starting sanity test happy flow");
        SanityTestDTO sanityTestDTO = generateSanityTest();
        SanityTestDTO sanityTestDTO1 = sanityTestRestClient.createSanityTest(sanityTestDTO);
        assertNotNull(sanityTestDTO1.getId());
        SanityTestDTO sanityTestDTO2 = sanityTestRestClient.readSanityTest(sanityTestDTO1.getId());
        assertNotNull(sanityTestDTO2);
        assertEquals(sanityTestDTO1, sanityTestDTO2);
        logger.debug("Sanity test passed successfully");
    }

    private SanityTestDTO generateSanityTest() {
        SanityTestDTO sanityTestDTO = new SanityTestDTO();
        sanityTestDTO.setEmail("sanity@test.com");
        return sanityTestDTO;
    }
}
