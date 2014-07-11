package com.sc101.imaggregator.facade.rest.impl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import com.knappsack.swagger4springweb.util.ScalaObjectMapper;
import com.sc101.imaggregator.common.config.CommonAppConfig;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * This configuration integrates with swagger in order to expose REST API documentation.
 * The integration is done using swagger4spring-web plugin (site: https://github.com/wkennedy/swagger4spring-web).
 * This plugin creates a controller under /api/resourceList.
 * If called, the controller will scan the REST APIs and return a swagger json document readable by a swagger-ui.
 *
 * Notice, this configuration is under the "documentation" spring profile.
 */
@Configuration
@PropertySource({"classpath:documentation.properties"})
@Profile(CommonAppConfig.PROFILE_DOCUMENTATION)
public class DocumentationConfig extends WebMvcConfigurerAdapter {

    // TODO Make this automatic
    @Value("${documentation.services.basePath:@null}")
    private String documentationServerBasePath;

    @Value("${documentation.services.version:1.0}")
    private String documentationVersion;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters ) {
        converters.add(converter());
    }

    @Bean
    public HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(scalaObjectMapper());
        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public ObjectMapper scalaObjectMapper() {
        return new ScalaObjectMapper();
    }

    @Bean
    public ApiDocumentationController apiDocumentationController() {
        ApiDocumentationController apiDocumentationController = new ApiDocumentationController();
        apiDocumentationController.setBasePath(documentationServerBasePath);
        apiDocumentationController.setBaseControllerPackage("com.sc101.imaggregator.facade.rest");
        apiDocumentationController.setBaseModelPackage("com.sc101.imaggregator.facade.rest");
        apiDocumentationController.setApiVersion(documentationVersion);
        ApiInfo apiInfo = new ApiInfo("IM Aggregator Application",
                                      "IM Aggregator application description",
                                      null, null, null, null);
        apiDocumentationController.setApiInfo(apiInfo);
        return apiDocumentationController;
    }
}
