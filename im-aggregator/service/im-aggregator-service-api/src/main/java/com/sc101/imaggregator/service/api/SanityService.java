package com.sc101.imaggregator.service.api;

import com.sc101.imaggregator.service.model.SanityTest;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Sanity Test Service Interface
 */
public interface SanityService {

    /**
     * Create Sanity Test
     * @param sanityTest Sanity Test to create
     * @return Created Sanity Test
     */
    SanityTest createSanityTest(SanityTest sanityTest);

    /**
     * Read Sanity Test by id
     * @param sanityTestId Sanity Test id
     * @return Read Sanity Test
     */
    SanityTest readSanityTest(String sanityTestId);
}
