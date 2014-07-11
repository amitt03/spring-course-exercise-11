package com.sc101.imaggregator.service.impl;

import com.sc101.imaggregator.dal.api.SanityTestDao;
import com.sc101.imaggregator.service.api.SanityService;
import com.sc101.imaggregator.service.model.SanityTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * Simple implementation.
 * Currently there is no additional logic for this service to perform so it just delegates the request to the DAL.
 *
 * Notice:
 * That currently there is no transaction management (assuming that stub dal is used)
 */
@Service
public class SanityServiceImpl implements SanityService {

    private Logger logger = LoggerFactory.getLogger(SanityServiceImpl.class);

    @Autowired
    private SanityTestDao sanityTestDao;

    @Override
    public SanityTest createSanityTest(SanityTest sanityTest) {
        SanityTest sanityTestResult = sanityTestDao.createSanityTest(sanityTest);
        return sanityTestResult;
    }

    @Override
    public SanityTest readSanityTest(String sanityTestId) {
        SanityTest sanityTest = sanityTestDao.readSanityTest(sanityTestId);
        return sanityTest;
    }
}
