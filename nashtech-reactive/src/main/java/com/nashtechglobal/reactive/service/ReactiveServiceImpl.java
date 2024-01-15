package com.nashtechglobal.reactive.service;

import com.nashtechglobal.reactive.configuration.ReactiveClientConfig;
import com.nashtechglobal.web.model.ExternalApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class implements the {@link ReactiveService} interface and provides
 * methods for making HTTP requests to external APIs using a reactive client.
 */
@Service
@Slf4j
public class ReactiveServiceImpl implements ReactiveService {

    /**
     * {@link ReactiveClientConfig} object used to configure reactive client.
     */
    private final ReactiveClientConfig clientConfig;

    /**
     * Creates a new instance of the {@link ReactiveServiceImpl} class.
     *
     * @param config The {@link ReactiveClientConfig} object
     *                         used to configure the reactive client.
     */
    public ReactiveServiceImpl(final ReactiveClientConfig config) {
        this.clientConfig = config;
    }

    /**
     * Executes a {@link Flux} API request to an external API using
     * the reactive client.
     *
     * @param apiRequest   The {@link ExternalApiRequest} object containing
     *                     information about the request.
     * @param responseType The {@link Class} object representing the expected
     *                     response type from the external API.
     * @param <R>          The type of the request expected from external API.
     * @param <T>          The type of the response object.
     * @return A {@link Flux} of {@link ResponseEntity} objects containing the
     *     response from the external API.
     */
    @Override
    public <R, T> Flux<ResponseEntity<T>> executeFluxApiRequest(
            final ExternalApiRequest<R> apiRequest, final Class<T> responseType,
            final ReactiveRequestCallback callback) {
        log.info("Sending request by FluxAPI");
        return clientConfig
                .createRequest(apiRequest)
                .body(BodyInserters.fromValue(apiRequest.getBody()))
                .exchangeToFlux(clientResponse -> {
                callback.onSuccess(clientResponse.statusCode().value());
                return clientResponse.bodyToFlux(responseType)
                .map(responseBody -> ResponseEntity
                .status(clientResponse.statusCode()).body(responseBody));
                })
                .doOnError(callback::onError);
    }

    /**
     * Executes a {@link Mono} API request to an external API using
     * the reactive client.
     *
     * @param apiRequest   The {@link ExternalApiRequest} object containing
     *                     information about the request.
     * @param responseType The {@link Class} object representing the expected
     *                     response type from the external API.
     * @param <R>          The type of the request expected from external API.
     * @param <T>          The type of the response object.
     * @return A {@link Mono} of {@link ResponseEntity} object containing the
     *     response from the external API.
     */
    @Override
    public <R, T> Mono<ResponseEntity<T>> executeMonoApiRequest(
            final ExternalApiRequest<R> apiRequest, final Class<T> responseType,
            final ReactiveRequestCallback requestCallback) {
        log.info("Sending request by MonoAPI");
        if (apiRequest.getBody() != null) {
            return clientConfig
                 .createRequest(apiRequest)
                 .body(BodyInserters.fromValue(apiRequest.getBody()))
                 .exchangeToMono(response -> response.toEntity(responseType))
                 .doOnError(requestCallback::onError)
                 .doOnSuccess(response -> requestCallback
                         .onSuccess(response.getStatusCode().value()));
        }
        return clientConfig.createRequest(apiRequest)
                .exchangeToMono(responses -> responses.toEntity(responseType))
                .doOnError(requestCallback::onError)
                .doOnSuccess(response -> requestCallback
                        .onSuccess(response.getStatusCode().value()));
    }
}
