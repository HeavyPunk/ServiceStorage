package com.zaordu.servicestorage.abstractions;

import com.zaordu.servicestorage.models.ServiceModel;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ServiceHandler {
    Set<ServiceModel> getServicesInfo();
    void addService(ServiceModel newService);
    void rewriteService(ServiceModel newModel);
    void setServices(List<ServiceModel> services);
    ServiceModel getServiceInfo(UUID id);
}
