package com.sc101.imaggregator.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Common application configuration.
 * - Loads spring beans using component scan.
 * - Creates a property source placeholder configurer.
 */
@Configuration
@ComponentScan("com.sc101.imaggregator")
public class CommonAppConfig {

    public static final String PROFILE_DOCUMENTATION = "documentation";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setNullValue("@null");
        return propertySourcesPlaceholderConfigurer;
    }
}
