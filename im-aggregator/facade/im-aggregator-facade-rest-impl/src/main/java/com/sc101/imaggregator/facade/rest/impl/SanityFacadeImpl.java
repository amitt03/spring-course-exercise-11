package com.sc101.imaggregator.facade.rest.impl;

import com.sc101.imaggregator.facade.rest.api.SanityFacade;
import com.sc101.imaggregator.facade.rest.api.dto.SanityTestDTO;
import com.sc101.imaggregator.service.api.SanityService;
import com.sc101.imaggregator.service.model.SanityTest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * This Facade exposes REST APIs for performing basic sanity tests
 */
@RestController
@RequestMapping(SanityFacadeImpl.RESOURCE)
@Api(basePath = SanityFacadeImpl.RESOURCE, value = SanityFacadeImpl.RESOURCE, description = "Sanity Facade Example")
public class SanityFacadeImpl implements SanityFacade {
    
    private Logger logger = LoggerFactory.getLogger(SanityFacadeImpl.class);

    public static final String RESOURCE = "/sanity";

    @Autowired
    private SanityService sanityService;

    @Autowired
    private Mapper mapper;


    @ApiOperation(value = "Create sanity test", httpMethod = "POST", response = SanityTestDTO.class, position = 0)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public SanityTestDTO createSanityTest(@Valid
                                          @RequestBody
                                          @ApiParam(value = "Sanity Test DTO")
                                          final SanityTestDTO sanityTestDTO) {
        logger.debug("Request to create sanity test: {}", sanityTestDTO);
        SanityTest sanityTest = mapper.map(sanityTestDTO, SanityTest.class);
        SanityTest sanityTestResult = sanityService.createSanityTest(sanityTest);
        SanityTestDTO sanityTestDTOResult = mapper.map(sanityTestResult, SanityTestDTO.class);
        logger.debug("Created sanity test: {}", sanityTestDTOResult);
        return sanityTestDTOResult;
    }

    @ApiOperation(value = "Read sanity test by id", httpMethod = "GET", response = SanityTestDTO.class, position = 1)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public SanityTestDTO readSanityTest(@PathVariable
                                        @ApiParam(value = "Sanity DTO id", name = "id")
                                        final String id) {
        logger.debug("Request to read sanity test {}", id);
        SanityTest sanityTest = sanityService.readSanityTest(id);
        SanityTestDTO sanityTestDTO = mapper.map(sanityTest, SanityTestDTO.class);
        return sanityTestDTO;
    }
}
