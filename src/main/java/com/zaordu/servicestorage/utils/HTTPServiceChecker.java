package com.zaordu.servicestorage.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HTTPServiceChecker {
    public ServiceStatus check(String url) {
        HttpResponse response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.of(60, SECONDS))
                    .GET()
                    .build();
            response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            return ServiceStatus.STOPPED;
        }
        if(response == null || response.statusCode() >= 500)
            return ServiceStatus.STOPPED;
        return  ServiceStatus.RUNNING;
    }
}
