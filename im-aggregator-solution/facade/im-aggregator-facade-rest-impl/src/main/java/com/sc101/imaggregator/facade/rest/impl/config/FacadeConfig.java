package com.sc101.imaggregator.facade.rest.impl.config;

import com.sc101.imaggregator.common.config.CommonAppConfig;
import com.sc101.imaggregator.facade.rest.impl.aop.ThrottlingAspect;
import org.dozer.DozerMapperWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
@PropertySource("${propsLocation:classpath:}throttling.properties")
@EnableWebMvc
public class FacadeConfig {

    @Value("${max.api.per.minute:5}")
    private int maxAPIsPerMinute;

    @Bean
    public DozerMapperWrapper dozerBeanMapper() {
        return new DozerMapperWrapper();
    }

    @Bean
    @Profile("!"+CommonAppConfig.PROFILE_DEVELOPMENT)
    public ThrottlingAspect throttlingAspect() {
        return new ThrottlingAspect(maxAPIsPerMinute);
    }
}
