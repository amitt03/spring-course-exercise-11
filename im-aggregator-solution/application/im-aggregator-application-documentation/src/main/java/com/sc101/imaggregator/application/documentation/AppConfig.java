package com.sc101.imaggregator.application.documentation;

import com.sc101.imaggregator.common.config.CommonAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Main application configuration.
 * Adds the embedded swagger-ui resources in order for them to be statically served by the web application.
 */
@Configuration
@EnableWebMvc
@Import(CommonAppConfig.class)
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
