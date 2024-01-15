package com.nashtechglobal.reactive.configuration;

import com.nashtechglobal.web.model.ExternalApiRequest;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReactiveClientConfigTest {
    @Mock
    private WebClient webClientMock;

    private ReactiveClientConfig reactiveWebClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @BeforeEach
    void setUp() {
        reactiveWebClient = new ReactiveClientConfig();
        ReflectionTestUtils.setField(
                reactiveWebClient, "client", webClientMock);
    }

    @Test
    @DisplayName("Test web client with GET Request")
    void test_web_client_get_request() {
        ExternalApiRequest<Object> externalApiRequest = ExternalApiRequest
                .builder()
                .headers(new HttpHeaders())
                .httpMethod(HttpMethod.GET)
                .url("/dummy")
                .build();

        when(webClientMock.method(isA(HttpMethod.class)))
                .thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri(isA(String.class)))
                .thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.headers(isA(Consumer.class)))
                .thenReturn(requestBodySpecMock);

        reactiveWebClient.createRequest(externalApiRequest);

        verify(webClientMock, times(1)).method(any(HttpMethod.class));
    }

    @Test
    @DisplayName("Test web client with POST Request")
    void test_web_client_post_request() {
        ExternalApiRequest<Object> externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(new HttpHeaders())
                .httpMethod(HttpMethod.POST)
                .url("/dummy")
                .build();

        when(webClientMock.method(isA(HttpMethod.class)))
                .thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri(isA(String.class)))
                .thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.headers(isA(Consumer.class)))
                .thenReturn(requestBodySpecMock);

        reactiveWebClient.createRequest(externalApiRequest);

        verify(webClientMock, times(1)).method(any(HttpMethod.class));
    }

    @Test
    @DisplayName("Test web client with PUT Request")
    void test_web_client_put_request() {
        ExternalApiRequest<Object> externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(new HttpHeaders())
                .httpMethod(HttpMethod.PUT)
                .url("/dummy")
                .build();

        when(webClientMock.method(isA(HttpMethod.class)))
                .thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri(isA(String.class)))
                .thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.headers(isA(Consumer.class)))
                .thenReturn(requestBodySpecMock);

        reactiveWebClient.createRequest(externalApiRequest);

        verify(webClientMock, times(1)).method(any(HttpMethod.class));
    }

    @Test
    @DisplayName("Test web client with DELETE Request")
    void test_web_client_delete_request() {
        ExternalApiRequest<Object> externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(new HttpHeaders())
                .httpMethod(HttpMethod.DELETE)
                .url("/dummy")
                .build();

        when(webClientMock.method(isA(HttpMethod.class)))
                .thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri(isA(String.class)))
                .thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.headers(isA(Consumer.class)))
                .thenReturn(requestBodySpecMock);

        reactiveWebClient.createRequest(externalApiRequest);

        verify(webClientMock, times(1)).method(any(HttpMethod.class));
    }
}
