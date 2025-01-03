package com.example;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testGetLoginForm() throws Exception {
        App.LoginHandler handler = new App.LoginHandler();
        HttpExchange exchange = mock(HttpExchange.class);

        when(exchange.getRequestMethod()).thenReturn("GET");
        OutputStream os = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(os);

        handler.handle(exchange);
        assertTrue(os.toString().contains("<h1>Login</h1>"));
    }

    @Test
    public void testPostLogin() throws Exception {
        App.LoginHandler handler = new App.LoginHandler();
        HttpExchange exchange = mock(HttpExchange.class);

        when(exchange.getRequestMethod()).thenReturn("POST");
        OutputStream os = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(os);

        handler.handle(exchange);
        assertEquals("Login successful!", os.toString().trim());
    }
}
