package com.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;  // ✅ Add this import
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private HttpExchange exchange;
    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
        exchange = mock(HttpExchange.class);
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(outputStream);
    }

    @Test
    public void testGetLoginForm() throws Exception {
        app.getLoginForm(exchange);
        verify(exchange).sendResponseHeaders(eq(200), anyLong());
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));
        // Update this assertion to allow charset in Content-Type
        String contentType = exchange.getResponseHeaders().getFirst("Content-Type");
        assertTrue(contentType.startsWith("text/html"));
    }

    @Test
    public void testPostLogin() throws Exception {
        when(exchange.getRequestBody()).thenReturn(new ByteArrayInputStream("username=admin&password=1234".getBytes(StandardCharsets.UTF_8)));
        app.postLogin(exchange);
        verify(exchange).sendResponseHeaders(eq(200), anyLong());
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));
        // Update this assertion to allow charset in Content-Type
        String contentType = exchange.getResponseHeaders().getFirst("Content-Type");
        assertTrue(contentType.startsWith("text/html"));

        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) exchange.getResponseBody();
        String response = outputStream.toString(StandardCharsets.UTF_8);
        assertEquals("<html><body>Login Success!</body></html>", response);
    }
}
