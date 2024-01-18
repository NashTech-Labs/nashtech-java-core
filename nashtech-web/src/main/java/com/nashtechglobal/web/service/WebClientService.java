package com.nashtechglobal.web.service;

import com.nashtechglobal.web.model.ExternalApiRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebClientService {

    /**
     * Inject Rest template.
     */
    private RestTemplate restTemplate;

    /**
     * Constructor to initialize the ResilienceServiceImpl
     * with a WebClientService.
     *
     * @param restTemplat The WebClientService to be used.
     */
    public WebClientService(
            final RestTemplate restTemplat) {
        this.restTemplate = restTemplat;
    }

    /**
     * @param apiRequest The request body containing the request URL,
     *    HTTP method, and request body.
     * @param responseType The type of response expected.
     * @return The ResponseEntity of the HTTP request as a generic type T.
     * @param <R> The type of response
     * @param <T> The type of request
     */
    public <R, T> ResponseEntity<R> getExternalApiResponse(
            final  ExternalApiRequest<T> apiRequest,
            final Class<R> responseType) {

        HttpEntity<T> requestEntity = null;
        if (apiRequest.getBody() != null || apiRequest.getHeaders() != null) {
            requestEntity = new HttpEntity<>(apiRequest.getBody(),
                    apiRequest.getHeaders());
        }

        return restTemplate.exchange(apiRequest.getUrl(),
                apiRequest.getHttpMethod(), requestEntity, responseType);
    }
}
