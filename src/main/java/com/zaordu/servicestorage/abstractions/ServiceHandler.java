package com.zaordu.servicestorage.abstractions;

import com.zaordu.servicestorage.models.ServiceModel;

import java.util.Set;
import java.util.UUID;

public interface ServiceHandler {
    Set<ServiceModel> getServicesInfo();
    void addService(ServiceModel newService);
    void rewriteService(ServiceModel newModel);
    ServiceModel getServiceInfo(UUID id);
}
