/**
 * Represents an error that occurred during a network request.
 */
package com.nashtechglobal.resilience.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    /**
     * The timestamp of the error.
     */
    private String timestamp;

    /**
     * The type of error that occurred.
     */
    private String errorType;

    /**
     * A message describing the error.
     */
    private String message;

    /**
     * The body of the response that caused the error.
     */
    private String body;

    /**
     * The HTTP status code of the response that caused the error.
     */
    private HttpStatusCode statusCode;

    /**
     * The HTTP headers of the response that caused the error.
     */
    private HttpHeaders headers;
}
