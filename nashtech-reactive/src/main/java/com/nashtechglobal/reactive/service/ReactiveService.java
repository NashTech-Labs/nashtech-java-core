package com.nashtechglobal.reactive.service;

import com.nashtechglobal.web.model.ExternalApiRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface provides methods for executing reactive API requests and
 * handling their responses.
 */
public interface ReactiveService {

    /**
     * Executes a reactive API request that returns stream of response entities.
     *
     * @param apiRequest   The {@link ExternalApiRequest} object that contains
     *                     information about the API request.
     * @param responseType The class object that represents the type of the
     *                     response object.
     * @param callback     The {@link ReactiveRequestCallback} object to be
     *                     called when the request is completed.
     * @param <R>          The type of the request object.
     * @param <T>          The type of the response object.
     * @return A {@link Flux} of {@link ResponseEntity} objects that represent
     *     the responses from the API.
     */
    <R, T> Flux<ResponseEntity<T>> executeFluxApiRequest(
            ExternalApiRequest<R> apiRequest, Class<T> responseType,
            ReactiveRequestCallback callback);

    /**
     * Executes a reactive API request that returns a single response entity.
     *
     * @param apiRequest   The {@link ExternalApiRequest} object that contains
     *                     information about the API request.
     * @param responseType The class object that represents the type of the
     *                     response object.
     * @param callback     The {@link ReactiveRequestCallback} object to be
     *                     called when the request is completed.
     * @param <R>          The type of the request object.
     * @param <T>          The type of the response object.
     * @return A {@link Mono} of {@link ResponseEntity} object that represents
     *     the response from the API.
     */
    <R, T> Mono<ResponseEntity<T>> executeMonoApiRequest(
            ExternalApiRequest<R> apiRequest, Class<T> responseType,
            ReactiveRequestCallback callback);
}
