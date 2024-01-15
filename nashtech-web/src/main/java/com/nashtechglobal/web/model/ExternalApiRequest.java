package com.nashtechglobal.web.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * This class represents a request to an external API.
 *
 * @param <T> the type of the request body
 */
@Builder
@Data
public class ExternalApiRequest<T> {
    /**
     * The URL of the external API.
     */
    private String url;

    /**
     * The HTTP method to be used for the request.
     */
    private HttpMethod httpMethod;

    /**
     * The request body.
     */
    private T body;

    /**
     * The headers to be included in the request.
     */
    private HttpHeaders headers;

}
