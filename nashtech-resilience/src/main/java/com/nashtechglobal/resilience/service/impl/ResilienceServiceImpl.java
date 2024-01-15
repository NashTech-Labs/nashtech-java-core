package com.nashtechglobal.resilience.service.impl;

import com.nashtechglobal.resilience.model.Error;
import com.nashtechglobal.resilience.model.ResilienceConstants;
import com.nashtechglobal.resilience.service.ResilienceService;
import com.nashtechglobal.web.model.ExternalApiRequest;
import com.nashtechglobal.web.service.WebClientService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.http.HttpConnectTimeoutException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * This class implements the ResilienceService class and all its methods.
 */
@Service
@Slf4j
public class ResilienceServiceImpl implements ResilienceService {
    /**
     * Status code for connection timeout.
     */
    public static final int CODE522 = 522;
    /**
     * Status code for internal server error.
     */
    public static final int CODE500 = 500;
    /**
     * webclientservice.
     */
    @Autowired
    private WebClientService webClientService;

    /**
     * {@inheritDoc}
     */
    @Override
    @CircuitBreaker(name = ResilienceConstants.ALL_RESILIENCE,
            fallbackMethod = ResilienceConstants.CIRCUIT_BREAKER_FALLBACK)
    @RateLimiter(name = ResilienceConstants.ALL_RESILIENCE,
            fallbackMethod = ResilienceConstants.RATE_LIMITER_FALLBACK)
    @Bulkhead(name = ResilienceConstants.ALL_RESILIENCE,
            fallbackMethod = ResilienceConstants.BULK_HEAD_FALLBACK)
    @Retry(name = ResilienceConstants.ALL_RESILIENCE,
            fallbackMethod = ResilienceConstants.RETRY_FALLBACK)
    public <R, T> ResponseEntity<R> callExternalApiWithAllResilience(
            final ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("All Resilience4j!!!");
        log.debug("All Resilience4j!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return webClientService.getExternalApiResponse(apiRequest,
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CircuitBreaker(name = ResilienceConstants.CIRCUIT_BREAKER,
            fallbackMethod = ResilienceConstants.CIRCUIT_BREAKER_FALLBACK)
    public <R, T> ResponseEntity<R> callExternalApiWithCircuitBreaker(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("Circuit Breaker!!!");
        log.debug("Circuit Breaker!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return webClientService.getExternalApiResponse(apiRequest,
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RateLimiter(name = ResilienceConstants.RATE_LIMITER,
            fallbackMethod = ResilienceConstants.RATE_LIMITER_FALLBACK)
    public <R, T> ResponseEntity<R> callExternalApiWithRateLimiter(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("RATE LIMITER!!!");
        log.debug("Rate Limiter!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return webClientService.getExternalApiResponse(apiRequest,
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Bulkhead(name = ResilienceConstants.BULK_HEAD,
            fallbackMethod = ResilienceConstants.BULK_HEAD_FALLBACK)
    public <R, T> ResponseEntity<R> callExternalApiWithBulkHead(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("Bulkhead!!!");
        log.debug("Bulkhead!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return webClientService.getExternalApiResponse(apiRequest,
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Retry(name = ResilienceConstants.RETRY,
            fallbackMethod = ResilienceConstants.RETRY_FALLBACK)
    public <R, T> ResponseEntity<R> callExternalApiWithRetry(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("Retry!!!");
        log.debug("Retry!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return webClientService.getExternalApiResponse(apiRequest,
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TimeLimiter(name = ResilienceConstants.TIME_LIMITER)
    public <R, T> CompletableFuture<ResponseEntity<R>>
    callExternalApiWithTimeLimiter(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("Time Limiter!!!");
        log.debug("Time Limiter!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return CompletableFuture.supplyAsync(() -> webClientService.
                getExternalApiResponse(apiRequest, responseType));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CircuitBreaker(name = ResilienceConstants.CIRCUIT_BREAKER,
            fallbackMethod = ResilienceConstants.CIRCUIT_BREAKER_FALLBACK)
    @Retry(name = ResilienceConstants.RETRY)
    public <R, T> ResponseEntity<R> callExternalApiWithCircuitBreakerAndRetry(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {
        log.info("CircuitBreaker & Retry!!!");
        log.debug("Circuit Breaker % Retry!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return webClientService.getExternalApiResponse(apiRequest,
                responseType);
    }

    /**
     * {@inheritDoc}
     */
    public <R, T> ResponseEntity<Object> circuitBreakerFallback(
            final   ExternalApiRequest<T> apiRequest,
            final Class<R> responseType,
            final Exception ex) {
        log.info("Circuit Breaker fallback!!!");
        log.debug("Circuit Breaker fallback!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return createErrorMessage(ex);
    }

    /**
     * {@inheritDoc}
     */
    public <R, T> ResponseEntity<Object> rateLimiterFallback(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType,
            final Exception ex) {
        log.info("RateLimiter from fallback!!!");
        log.debug("RateLimiter from fallback!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return createErrorMessage(ex);
    }

    /**
     * {@inheritDoc}
     */
    public <R, T> ResponseEntity<Object> bulkHeadFallback(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType,
            final Exception ex) {
        log.info("BulkHead from fallback!!!");
        log.debug("BulkHead from fallback!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return createErrorMessage(ex);
    }

    /**
     * {@inheritDoc}
     */
    public <R, T> ResponseEntity<Object> timeLimiterFallback(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType,
            final Exception ex) {
        log.info("TimeLimiter from fallback!!!");
        log.debug("TimeLimiter from fallback!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return createErrorMessage(ex);
    }

    /**
     * {@inheritDoc}
     */
    public <R, T> ResponseEntity<Object> retryFallback(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType,
            final Exception ex) {
        log.info("Retry from fallback!!!");
        log.debug("Retry from fallback!!! Request: {}, Response: {}",
                apiRequest, responseType);
        return createErrorMessage(ex);
    }

    /**
     * Creates an error message with the information from the given exception.
     *
     * @param exception the exception to generate the error message from.
     * @return a ResponseEntity containing the error message.
     */
    private ResponseEntity<Object> createErrorMessage(
            final Exception exception) {
        log.info("Exception Class {}", exception.getClass().getName());
        HttpStatusCode statusCode = null;
        HttpHeaders responseHeaders = null;
        String responseBodyAsString = null;
        if (exception instanceof HttpClientErrorException clientError) {
            statusCode = clientError.getStatusCode();
            responseHeaders = clientError.getResponseHeaders();
            responseBodyAsString = clientError.getResponseBodyAsString();
        } else if (exception instanceof HttpServerErrorException serverError) {
            statusCode = serverError.getStatusCode();
            responseHeaders = serverError.getResponseHeaders();
            responseBodyAsString = serverError.getResponseBodyAsString();

        } else if (exception instanceof HttpConnectTimeoutException timeOut) {
            statusCode = HttpStatusCode.valueOf(CODE522);
            responseBodyAsString = timeOut.getLocalizedMessage();
        } else {
            statusCode = HttpStatusCode.valueOf(CODE500);
            responseBodyAsString = exception.getClass().getName();
        }

        Error errorMessage = Error.builder().timestamp(new Date().toString())
                .errorType(exception.getClass().getName())
                .message(exception.getMessage())
                .statusCode(statusCode)
                .headers(responseHeaders)
                .body(responseBodyAsString)
                .build();
        return new ResponseEntity<>(errorMessage,
                HttpStatusCode.valueOf(statusCode.value()));
    }

}
