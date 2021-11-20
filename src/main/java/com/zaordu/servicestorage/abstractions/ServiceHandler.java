package com.zaordu.servicestorage.abstractions;

import com.zaordu.servicestorage.models.ServiceModel;

import java.util.Set;

public interface ServiceHandler {
    Set<ServiceModel> getServicesInfo();
    void addService(ServiceModel newService);
    void rewriteService(ServiceModel newModel);
}
