package com.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    // Constants for response headers
    private static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";
    private static final String HTML_TEMPLATE = "<html><body><form method='POST' action='/login'>" +
                                                "<input type='text' name='username' />" +
                                                "<input type='password' name='password' />" +
                                                "<button type='submit'>Login</button></form></body></html>";
    private static final String SUCCESS_RESPONSE = "<html><body>Login Successful!</body></html>";

    // Method to handle GET request and return a login form
    public void getLoginForm(HttpExchange exchange) throws IOException {
        try {
            // Set headers
            setResponseHeaders(exchange);

            // Send response
            String response = HTML_TEMPLATE;
            sendResponse(exchange, response);
        } catch (IOException e) {
            LOGGER.severe("Error during GET request handling: " + e.getMessage());
            throw e;
        }
    }

    // Method to handle POST request for login
    public void postLogin(HttpExchange exchange) throws IOException {
        try {
            // Set headers
            setResponseHeaders(exchange);

            // Send response
            String response = SUCCESS_RESPONSE;
            sendResponse(exchange, response);
        } catch (IOException e) {
            LOGGER.severe("Error during POST request handling: " + e.getMessage());
            throw e;
        }
    }

    // Helper method to set response headers
    private void setResponseHeaders(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", CONTENT_TYPE_HTML);
    }

    // Helper method to send response
    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
