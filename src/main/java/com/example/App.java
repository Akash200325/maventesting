package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Add a context to serve the login form
        server.createContext("/login", new LoginHandler());

        // Start the server
        server.setExecutor(null);
        server.start();
        System.out.println("Server is running at http://localhost:8080/login");
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response;
            switch (exchange.getRequestMethod()) {
                case "GET":
                    response = getLoginForm();
                    sendResponse(exchange, response, "text/html");
                    break;

                case "POST":
                    // Process login submission (dummy handling for demonstration)
                    response = "Login successful!";
                    sendResponse(exchange, response, "text/plain");
                    break;

                default:
                    // Handle unsupported methods
                    response = "Method Not Allowed";
                    exchange.sendResponseHeaders(405, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                    break;
            }
        }

        private String getLoginForm() {
            return """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <title>Login Page</title>
                    </head>
                    <body>
                        <h1>Login</h1>
                        <form action="/login" method="post">
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username"><br><br>
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password"><br><br>
                            <button type="submit">Submit</button>
                        </form>
                    </body>
                    </html>
                    """;
        }

        private void sendResponse(HttpExchange exchange, String response, String contentType) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
