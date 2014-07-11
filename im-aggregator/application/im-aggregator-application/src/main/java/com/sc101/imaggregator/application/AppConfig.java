package com.sc101.imaggregator.application;

import com.sc101.imaggregator.common.config.CommonAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Main application configuration
 */
@Configuration
@EnableWebMvc
@Import(CommonAppConfig.class)
public class AppConfig {
}
