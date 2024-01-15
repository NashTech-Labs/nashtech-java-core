package com.nashtechglobal.resilience.model;

/**
 * A class that contains constants used in the resilience framework.
 */
public final class ResilienceConstants {
    private ResilienceConstants() {

    }

    /**
     * The name of the all resilience configuration.
     */
    public static final String ALL_RESILIENCE = "allresilience";

    /**
     * The name of the circuit breaker configuration.
     */
    public static final String CIRCUIT_BREAKER = "circuit_breaker";

    /**
     * The name of the rate limiter configuration.
     */
    public static final String RATE_LIMITER = "ratelimiter";

    /**
     * The name of the bulkhead configuration.
     */
    public static final String BULK_HEAD = "bulkhead";

    /**
     * The name of the retry configuration.
     */
    public static final String RETRY = "retry";

    /**
     * The name of the time limiter configuration.
     */
    public static final String TIME_LIMITER = "timelimiter";

    /**
     * The name of the circuit breaker fallback method.
     */
    public static final String CIRCUIT_BREAKER_FALLBACK =
            "circuitBreakerFallback";

    /**
     * The name of the rate limiter fallback method.
     */
    public static final String RATE_LIMITER_FALLBACK = "rateLimiterFallback";

    /**
     * The name of the bulkhead fallback method.
     */
    public static final String BULK_HEAD_FALLBACK = "bulkHeadFallback";

    /**
     * The name of the retry fallback method.
     */
    public static final String RETRY_FALLBACK = "retryFallback";

    /**
     * The name of the time limiter fallback method.
     */
    public static final String TIME_LIMITER_FALLBACK = "timeLimiterFallback";
}
