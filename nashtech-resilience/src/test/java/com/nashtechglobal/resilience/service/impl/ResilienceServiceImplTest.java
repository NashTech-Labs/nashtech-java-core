package com.nashtechglobal.resilience.service.impl;

import com.nashtechglobal.web.model.ExternalApiRequest;
import com.nashtechglobal.web.service.WebClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.http.HttpConnectTimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ResilienceServiceImplTest {

    @Mock
    private WebClientService webClientService;

    @InjectMocks
    private ResilienceServiceImpl resilienceService;

    private  ExternalApiRequest request;

    @Test
    void testExternalApiWithCircuitBreakerSuccess() {

        ResponseEntity<String> response = new ResponseEntity<>("15", HttpStatus.OK);
        when(webClientService.getExternalApiResponse(request,String.class))
                .thenReturn(response);
        ResponseEntity<String> actualResponse = resilienceService.callExternalApiWithCircuitBreaker(request, String.class);
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        assertEquals("15", actualResponse.getBody());
    }
    @Test
    void testExternalApiWithCircuitBreakerFailure() {

        when(webClientService.getExternalApiResponse(request, String.class))
                .thenThrow(new RuntimeException("Failed to call external API"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            resilienceService.callExternalApiWithCircuitBreaker(request, String.class);
        });
        String expectedMessage = "Failed to call external API";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testExternalApiWithCircuitBreakerFallback() {

        Exception ex = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<Object> result = resilienceService.circuitBreakerFallback(request, String.class, ex);
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    void testExternalApiWithRateLimiterSuccess() {

        ResponseEntity<String> response = new ResponseEntity<>("15", HttpStatus.OK);
        when(webClientService.getExternalApiResponse(request, String.class))
                .thenReturn(response);
        ResponseEntity<String> actualResponse = resilienceService.callExternalApiWithRateLimiter(request, String.class);
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        assertEquals("15", actualResponse.getBody());
    }
    @Test
    void testExternalApiWithRateLimiterFailure() {

        when(webClientService.getExternalApiResponse(request, String.class))
                .thenThrow(new RuntimeException("Failed to call external API"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            resilienceService.callExternalApiWithRateLimiter(request, String.class);
        });
        String expectedMessage = "Failed to call external API";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testExternalApiWithRateLimiterFallback() {

        Exception ex = new HttpConnectTimeoutException("Connection time out");
        ResponseEntity<Object> result = resilienceService.rateLimiterFallback(request, String.class, ex);
        assertNotNull(result);
        assertEquals(HttpStatusCode.valueOf(522), result.getStatusCode());
    }

    @Test
    void testExternalApiWithBulkHeadSuccess() {

        ResponseEntity<String> response = new ResponseEntity<>("15", HttpStatus.OK);
        when(webClientService.getExternalApiResponse(request, String.class))
                .thenReturn(response);
        ResponseEntity<String> actualResponse = resilienceService.callExternalApiWithBulkHead(request, String.class);
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        assertEquals("15", actualResponse.getBody());
    }

    @Test
    void testExternalApiWithBulkheadFailure() {

        when(webClientService.getExternalApiResponse(request, String.class))
                .thenThrow(new RuntimeException("Failed to call external API"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            resilienceService.callExternalApiWithBulkHead(request, String.class);
        });
        String expectedMessage = "Failed to call external API";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testExternalApiWithBulkheadFallback() {

        Exception ex = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<Object> result = resilienceService.bulkHeadFallback(request, String.class, ex);
        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    void testExternalApiWithRetrySuccess() {

        ResponseEntity<String> response = new ResponseEntity<>("15", HttpStatus.OK);
        when(webClientService.getExternalApiResponse(request, String.class))
                .thenReturn(response);
        ResponseEntity<String> actualResponse = resilienceService.callExternalApiWithRetry(request, String.class);
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        assertEquals("15", actualResponse.getBody());
    }
    @Test
    void testExternalApiWithRetryFailure() {
        when(webClientService.getExternalApiResponse(request, String.class))
                .thenThrow(new RuntimeException("Failed to call external API"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            resilienceService.callExternalApiWithRetry(request, String.class);
        });
        String expectedMessage = "Failed to call external API";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testRetryFallback() {

        Exception ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> result = resilienceService.retryFallback(request, String.class, ex);
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testExternalApiWithCircuitBreakerAndRetrySuccess() {

        ResponseEntity<String> response = new ResponseEntity<>("15", HttpStatus.OK);
        when(webClientService.getExternalApiResponse(request, String.class))
                .thenReturn(response);
        ResponseEntity<String> actualResponse = resilienceService.callExternalApiWithCircuitBreakerAndRetry(request, String.class);
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        assertEquals("15", actualResponse.getBody());
    }
    @Test
    void testExternalApiWithCircuitBreakerAndRetryFailure() {
        when(webClientService.getExternalApiResponse(request, String.class))
                .thenThrow(new RuntimeException("Failed to call external API"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            resilienceService.callExternalApiWithCircuitBreakerAndRetry(request, String.class);
        });
        String expectedMessage = "Failed to call external API";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}