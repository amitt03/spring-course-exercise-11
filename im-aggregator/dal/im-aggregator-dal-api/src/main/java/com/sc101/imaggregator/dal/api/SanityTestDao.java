package com.sc101.imaggregator.dal.api;

import com.sc101.imaggregator.service.model.SanityTest;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Defines APIs for the DAL layer to implement
 */
public interface SanityTestDao {

    /***
     * Create sanity test (persist)
     * @param sanityTest Sanity Test to persist
     * @return Created sanity test
     */
    SanityTest createSanityTest(SanityTest sanityTest);

    /**
     * @param sanityTestId Sanity Test id
     * @return Sanity Test that belongs to the input id
     */
    SanityTest readSanityTest(String sanityTestId);
}
