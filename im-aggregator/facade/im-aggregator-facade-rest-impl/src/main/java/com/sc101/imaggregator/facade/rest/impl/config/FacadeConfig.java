package com.sc101.imaggregator.facade.rest.impl.config;

import org.dozer.DozerMapperWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * This is the facade configuration.
 * Currently only dozer is configured for bean transformation
 */
@Configuration
@EnableMBeanExport(defaultDomain = "com.sc101")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class FacadeConfig {

    @Bean
    public DozerMapperWrapper dozerBeanMapper() {
        return new DozerMapperWrapper();
    }
}
