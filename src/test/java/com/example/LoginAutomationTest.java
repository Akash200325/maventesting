package com.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.OutputStream;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private HttpExchange exchange;
    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();

        // Mock HttpExchange
        exchange = mock(HttpExchange.class);

        // Mock Headers
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);

        // Mock OutputStream
        OutputStream outputStream = mock(OutputStream.class);
        when(exchange.getResponseBody()).thenReturn(outputStream);
    }

    @Test
    public void testGetLoginForm() throws Exception {
        app.getLoginForm(exchange);

        // Verify that headers are set correctly
        verify(exchange).getResponseHeaders();
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));

        // Verify response content is sent
        verify(exchange.getResponseBody(), atLeastOnce()).write(any(byte[].class), eq(0), anyInt());
    }

    @Test
    public void testPostLogin() throws Exception {
        app.postLogin(exchange);

        // Verify that headers are set correctly
        verify(exchange).getResponseHeaders();
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));

        // Verify response content is sent
        verify(exchange.getResponseBody(), atLeastOnce()).write(any(byte[].class), eq(0), anyInt());
    }
}
