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
            if ("GET".equals(exchange.getRequestMethod())) {
                String htmlResponse = """
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

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(htmlResponse.getBytes());
                os.close();
            } else if ("POST".equals(exchange.getRequestMethod())) {
                // Process login submission (dummy handling for demonstration)
                String response = "Login successful!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
