package com.zaordu.servicestorage.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HTTPServiceChecker {
    public ServiceStatus check(String url) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.of(60, SECONDS))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ServiceStatus.STOPPED;
    }
}
