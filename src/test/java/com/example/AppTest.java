package com.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private HttpExchange exchange;
    private App app;  // Assuming App is your main class with getLoginForm() and postLogin() methods

    @BeforeEach
    public void setUp() {
        // Initialize the class under test
        app = new App();

        // Mock the HttpExchange object
        exchange = mock(HttpExchange.class);

        // Mock the Headers object and set it to return a valid instance
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);

        // Mock the OutputStream object
        OutputStream outputStream = mock(OutputStream.class);
        when(exchange.getResponseBody()).thenReturn(outputStream);
    }

    @Test
    public void testGetLoginForm() throws Exception {
        // Call the method under test
        app.getLoginForm(exchange);

        // Verify headers are set correctly
        verify(exchange).getResponseHeaders();
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));
    }

    @Test
    public void testPostLogin() throws Exception {
        // Call the method under test
        app.postLogin(exchange);

        // Verify the response headers and output
        verify(exchange).getResponseHeaders();
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));

        // Match the exact byte array written to the response body
        byte[] expectedResponse = "<html><body>Login Success!</body></html>".getBytes(StandardCharsets.UTF_8);
        verify(exchange.getResponseBody()).write(eq(expectedResponse), eq(0), eq(expectedResponse.length));
    }
}
