package com.nastechglobal.web;

import com.nashtechglobal.web.model.ExternalApiRequest;
import com.nashtechglobal.web.service.WebClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {WebClientService.class, RestTemplate.class})
class WebClientServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private WebClientService webClientService;

    private static final String URL = "http://localhost";
    private static final String BODY = "Test content";

    private HttpEntity httpEntity;
    private HttpEntity httpEntity1;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        httpEntity = new HttpEntity<>(BODY, HttpHeaders.EMPTY);
        httpEntity1=new HttpEntity<>(null, HttpHeaders.EMPTY);
    }

    @Test
    void test() {
        ExternalApiRequest<Object> testContentWithBody = ExternalApiRequest.builder()
                .url(URL)
                .httpMethod(HttpMethod.GET)
                .body(BODY)
                .headers(HttpHeaders.EMPTY)
                .build();

        ExternalApiRequest<Object> testContentWithoutBody = ExternalApiRequest.builder()
                .url(URL)
                .httpMethod(HttpMethod.GET)
                .headers(HttpHeaders.EMPTY)
                .build();

        // Test case with body
        when(restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class))
                .thenReturn(ResponseEntity.ok(BODY));
        ResponseEntity<String> externalApiResponseWithBody = webClientService.getExternalApiResponse(testContentWithBody, String.class);
        assertEquals(HttpStatusCode.valueOf(200), externalApiResponseWithBody.getStatusCode());

        // Test case without body
        when(restTemplate.exchange(URL, HttpMethod.GET, httpEntity1, String.class))
                .thenReturn(ResponseEntity.ok(BODY));
        ResponseEntity<String> externalApiResponseWithoutBody = webClientService.getExternalApiResponse(testContentWithoutBody, String.class);
        assertEquals(HttpStatusCode.valueOf(200), externalApiResponseWithoutBody.getStatusCode());
    }
}