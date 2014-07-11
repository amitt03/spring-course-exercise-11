package com.sc101.imaggregator.facade.rest.api;

import com.sc101.imaggregator.facade.rest.api.dto.SanityTestDTO;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * REST Facade API for Sanity Test
 */
public interface SanityFacade {

    /**
     * Create Sanity Test
     * @param sanityTestDTO Sanity Test to create
     * @return Created Sanity Test
     */
    SanityTestDTO createSanityTest(SanityTestDTO sanityTestDTO);

    /**
     * Read Sanity Test by id
     * @param id Sanity Test id
     * @return Read Sanity Test
     */
    SanityTestDTO readSanityTest(String id);
}
