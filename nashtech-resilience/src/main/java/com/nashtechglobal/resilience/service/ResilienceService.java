package com.nashtechglobal.resilience.service;

import com.nashtechglobal.web.model.ExternalApiRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

/**
 * This ResilienceService interface provides methods of
 * generic type which are being implemented
 * in the ResilenceServiceImpl class.
 */
public interface ResilienceService {

    /**
     * This method is used to call an external API with
     * all possible resilience features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> ResponseEntity<R> callExternalApiWithAllResilience(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

    /**
     * This method is used to call an external API with
     * the Circuit Breaker features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> ResponseEntity<R> callExternalApiWithCircuitBreaker(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

    /**
     * This method is used to call an external API
     * with the RateLimiter features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> ResponseEntity<R> callExternalApiWithRateLimiter(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

    /**
     * This method is used to call an external API with the BulkHead features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> ResponseEntity<R> callExternalApiWithBulkHead(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

    /**
     * This method is used to call an external API with the Retry features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> ResponseEntity<R> callExternalApiWithRetry(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

    /**
     * This method is used to call an external API
     * with the TimeLimiter features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> CompletableFuture<ResponseEntity<R>> callExternalApiWithTimeLimiter(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

    /**
     * This method is used to call an external API with the
     * Circuit Breaker and Retry features.
     *
     * @param apiRequest   the api request to the external API.
     * @param <R>          The type of response
     * @param <T>          The type of request
     * @param responseType type of response.
     * @return ResponseEntity the response from the external API.
     */
    <R, T> ResponseEntity<R> callExternalApiWithCircuitBreakerAndRetry(
            ExternalApiRequest<T> apiRequest, Class<R> responseType);

}
