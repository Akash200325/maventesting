package com.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        // Initialize the class under test
        app = new App();

        // Mock the HttpExchange object
        exchange = mock(HttpExchange.class);

        // Mock the Headers object
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);

        // Mock the OutputStream object with a real implementation to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(outputStream);
    }

    @Test
    public void testGetLoginForm() throws Exception {
        // Call the method under test
        app.getLoginForm(exchange);

        // Verify that the response headers were set
        verify(exchange).sendResponseHeaders(eq(200), anyLong());
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));
        assertEquals("text/html", exchange.getResponseHeaders().getFirst("Content-Type"));

        // Capture the output from the response body
        OutputStream outputStream = exchange.getResponseBody();
        assertNotNull(outputStream);
    }

    @Test
    public void testPostLogin() throws Exception {
        // Prepare the request body
        when(exchange.getRequestBody()).thenReturn(new ByteArrayInputStream("username=admin&password=1234".getBytes(StandardCharsets.UTF_8)));

        // Call the method under test
        app.postLogin(exchange);

        // Verify that the response headers were set
        verify(exchange).sendResponseHeaders(eq(200), anyLong());
        assertTrue(exchange.getResponseHeaders().containsKey("Content-Type"));
        assertEquals("text/html", exchange.getResponseHeaders().getFirst("Content-Type"));

        // Capture and verify the response body
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) exchange.getResponseBody();
        String response = outputStream.toString(StandardCharsets.UTF_8);
        assertEquals("<html><body>Login Success!</body></html>", response);
    }
}
