package com.nashtechglobal.reactive.configuration;

import com.nashtechglobal.web.model.ExternalApiRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

/**
 * This class provides configuration for a reactive client that can make
 * requests to an external API.
 */
@Component
public class ReactiveClientConfig {

    /**
     * The {@link WebClient} object used to make requests to the external API.
     */
    private final WebClient client;

    /**
     * Creates a new instance of the {@link ReactiveClientConfig} class.
     * Initializes {@link #client} by calling {@link WebClient#create()}.
     */
    public ReactiveClientConfig() {
        this.client = WebClient.create();
    }

    /**
     * Creates a new {@link RequestBodySpec} object that can be used to make a
     * request to an external API.
     *
     * @param apiRequest The {@link ExternalApiRequest} object containing
     *                   information about the request.
     * @param <R>        The type of the response expected from external API.
     * @return {@link RequestBodySpec} object that can be used to make request.
     */

    public <R> RequestBodySpec createRequest(
            final ExternalApiRequest<R> apiRequest) {
        return this.client.method(apiRequest.getHttpMethod())
                .uri(apiRequest.getUrl())
                .headers(headers -> headers.addAll(apiRequest.getHeaders()));
    }
}
