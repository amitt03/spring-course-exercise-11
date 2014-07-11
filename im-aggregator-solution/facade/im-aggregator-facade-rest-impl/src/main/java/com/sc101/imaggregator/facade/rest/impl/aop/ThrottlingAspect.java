package com.sc101.imaggregator.facade.rest.impl.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Amit Tal
 * @since 5/7/2014
 */
@Aspect
@ManagedResource
public class ThrottlingAspect {

    private Logger logger = LoggerFactory.getLogger(ThrottlingAspect.class);

    private ConcurrentHashMap<Date, AtomicInteger> counter;
    private int maxCallsPerMinute;

    public ThrottlingAspect(int maxCallsPerMinute) {
        this.counter = new ConcurrentHashMap<>();
        this.maxCallsPerMinute = maxCallsPerMinute;
        logger.info("Initialized throttling mechanism which limits to {} api calls per minute", this.maxCallsPerMinute);
    }

    @Before("@annotation(com.sc101.imaggregator.facade.rest.impl.aop.Throttling)")
    public void checkThrottling(JoinPoint jp) {
        // Current date rounded to minutes
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date currentTimeRoundedToMinutes = calendar.getTime();
        // Add to map
        counter.putIfAbsent(currentTimeRoundedToMinutes, new AtomicInteger(0));
        AtomicInteger currentCounter = (AtomicInteger) this.counter.get(currentTimeRoundedToMinutes);
        int counter = currentCounter.incrementAndGet();
        if (counter > this.maxCallsPerMinute) {
            throw new ThrottlingException("Too many request at " + currentTimeRoundedToMinutes +
                                          "! ["+counter+"/"+this.maxCallsPerMinute+"]");
        }
    }

    @ManagedAttribute
    public ConcurrentHashMap<Date, AtomicInteger> getCounter() {
        return this.counter;
    }
}
