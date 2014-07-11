package com.sc101.imaggregator.dal.stub.impl;

import com.sc101.imaggregator.dal.api.SanityTestDao;
import com.sc101.imaggregator.service.model.SanityTest;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * A STUB implementation of the DAL API
 * This stub holds all Sanity Tests in-memory
 *
 * Notice: This stub is not to be used in a real/production system
 */
@Repository
public class SanityTestStubDaoImpl implements SanityTestDao {

    // Hashmap for holding SanityTest per id
    private ConcurrentHashMap<String, SanityTest> sanityTestsCache;

    public SanityTestStubDaoImpl() {
        sanityTestsCache = new ConcurrentHashMap<>();
    }

    @Override
    public SanityTest createSanityTest(SanityTest sanityTest) {
        sanityTestsCache.put(sanityTest.getId(), sanityTest);
        return sanityTest;
    }

    @Override
    public SanityTest readSanityTest(String sanityTestId) {
        SanityTest sanityTest = sanityTestsCache.get(sanityTestId);
        return sanityTest;
    }
}
