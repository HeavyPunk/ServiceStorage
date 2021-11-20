package com.zaordu.servicestorage.utils;

import com.zaordu.servicestorage.models.ServiceModel;

import java.util.UUID;

public class ServiceModelFactory {
    public static ServiceModel CreateService(String id, String name, String link, ServiceStatus status){
        var service = new ServiceModel();
        service.serviceId = UUID.fromString(id);
        service.serviceName = name;
        service.link = link;
        service.serviceStatus = status;

        return service;
    }
}
