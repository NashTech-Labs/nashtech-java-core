package com.nashtechglobal.reactive.service;

import com.nashtechglobal.reactive.configuration.ReactiveClientConfig;
import com.nashtechglobal.web.model.ExternalApiRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReactiveServiceImplTest {

    @Mock
    private ReactiveClientConfig reactiveWebClientMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private ReactiveRequestCallback callback;

    @InjectMocks
    private ReactiveServiceImpl reactiveService;

    @BeforeEach
    void setUp() {
        reactiveService = new ReactiveServiceImpl(reactiveWebClientMock);
    }

    @Test
    @DisplayName("Test Flux Api request with GET method")
    void test_fluxApiRequest_with_getMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.GET)
                .url("/dummy")
                .build();

        Flux<ResponseEntity<List>> mockResponse = Flux
                .just(ResponseEntity.ok(Arrays.asList(data)));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.exchangeToFlux(isA(Function.class)))
                .thenReturn(mockResponse);

        Flux<ResponseEntity<List>> responseEntityFlux = reactiveService
                .executeFluxApiRequest(externalApiRequest, List.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()).get(0))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.GET)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test Flux Api request with PUT method")
    void test_fluxApiRequest_with_putMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.PUT)
                .url("/dummy")
                .build();

        Flux<ResponseEntity<List>> mockResponse =
                Flux.just(ResponseEntity.ok(Arrays.asList(data)));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.exchangeToFlux(isA(Function.class)))
                .thenReturn(mockResponse);

        Flux<ResponseEntity<List>> responseEntityFlux = reactiveService
                .executeFluxApiRequest(externalApiRequest, List.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()).get(0))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.PUT)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test Flux Api request with POST method")
    void test_fluxApiRequest_with_postMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.POST)
                .url("/dummy")
                .build();

        Flux<ResponseEntity<List>> mockResponse =
                Flux.just(ResponseEntity.ok(Arrays.asList(data)));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.exchangeToFlux(isA(Function.class)))
                .thenReturn(mockResponse);

        Flux<ResponseEntity<List>> responseEntityFlux = reactiveService
                .executeFluxApiRequest(externalApiRequest, List.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()).get(0))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.POST)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test Flux Api request with DELETE method")
    void test_fluxApiRequest_with_deleteMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.DELETE)
                .url("/dummy")
                .build();

        Flux<ResponseEntity<List>> mockResponse =
                Flux.just(ResponseEntity.ok(Arrays.asList(data)));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.exchangeToFlux(isA(Function.class)))
                .thenReturn(mockResponse);

        Flux<ResponseEntity<List>> responseEntityFlux = reactiveService
                .executeFluxApiRequest(externalApiRequest, List.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()).get(0))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.DELETE)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test Mono Api request with GET method")
    void test_monoApiRequest_with_getMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.GET)
                .url("/dummy")
                .build();

        Mono<ResponseEntity<String>> mockResponse =
                Mono.just(ResponseEntity.ok(data));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);

        when(requestHeadersSpecMock.exchangeToMono(isA(Function.class)))
                .thenReturn(mockResponse);

        Mono<ResponseEntity<String>> responseEntityFlux = reactiveService
                .executeMonoApiRequest(externalApiRequest, String.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.GET)
                .verifyComplete();

    }

    @Test
    @DisplayName("Test Mono Api request without body and with GET method")
    void test_monoApiRequest_without_body_and_get_method() {

        String responseData = "dummy response";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");

        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .headers(headers)
                .httpMethod(HttpMethod.GET)
                .url("/dummy")
                .build();

        Mono<ResponseEntity<String>> mockResponse =
                Mono.just(ResponseEntity.ok(responseData));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.exchangeToMono(isA(Function.class)))
                .thenReturn(mockResponse);

        Mono<ResponseEntity<String>> responseEntityFlux = reactiveService
                .executeMonoApiRequest(externalApiRequest, String.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && responseData.equals(Objects
                                .requireNonNull(entity.getBody()))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.GET)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test Mono Api request with POST method")
    void test_monoApiRequest_with_postMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.POST)
                .url("/dummy")
                .build();

        Mono<ResponseEntity<String>> mockResponse =
                Mono.just(ResponseEntity.ok(data));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);

        when(requestHeadersSpecMock.exchangeToMono(isA(Function.class)))
                .thenReturn(mockResponse);

        Mono<ResponseEntity<String>> responseEntityFlux = reactiveService
                .executeMonoApiRequest(externalApiRequest, String.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.POST)
                .verifyComplete();

    }

    @Test
    @DisplayName("Test Flux Api request with PUT method")
    void test_monoApiRequest_with_putMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.PUT)
                .url("/dummy")
                .build();

        Mono<ResponseEntity<String>> mockResponse
                = Mono.just(ResponseEntity.ok(data));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);

        when(requestHeadersSpecMock.exchangeToMono(isA(Function.class)))
                .thenReturn(mockResponse);

        Mono<ResponseEntity<String>> responseEntityFlux = reactiveService
                .executeMonoApiRequest(externalApiRequest, String.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.PUT)
                .verifyComplete();

    }

    @Test
    @DisplayName("Test Mono Api request with DELETE method")
    void test_monoApiRequest_with_deleteMethod() {

        String data = "dummy value";
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");
        ExternalApiRequest externalApiRequest = ExternalApiRequest
                .builder()
                .body("{\"body\":\"request body\"}")
                .headers(headers)
                .httpMethod(HttpMethod.DELETE)
                .url("/dummy")
                .build();

        Mono<ResponseEntity<String>> mockResponse
                = Mono.just(ResponseEntity.ok(data));

        when(reactiveWebClientMock.createRequest(externalApiRequest))
                .thenReturn(requestBodySpecMock);

        when(requestBodySpecMock.body(isA(BodyInserter.class)))
                .thenReturn(requestHeadersSpecMock);

        when(requestHeadersSpecMock.exchangeToMono(isA(Function.class)))
                .thenReturn(mockResponse);

        Mono<ResponseEntity<String>> responseEntityFlux = reactiveService
                .executeMonoApiRequest(externalApiRequest, String.class, callback);

        StepVerifier.create(responseEntityFlux)
                .expectNextMatches(
                        entity -> entity.getStatusCode() == HttpStatus.OK
                                && data.equals(Objects
                                .requireNonNull(entity.getBody()))
                                && externalApiRequest
                                .getHttpMethod() == HttpMethod.DELETE)
                .verifyComplete();

    }
}
