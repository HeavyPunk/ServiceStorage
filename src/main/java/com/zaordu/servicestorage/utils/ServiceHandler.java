package com.zaordu.servicestorage.utils;

import com.zaordu.servicestorage.models.ServiceModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//TODO:Добавить локкеры на services
public class ServiceHandler implements com.zaordu.servicestorage.abstractions.ServiceHandler {
    private HashMap<UUID, ServiceModel> services = new HashMap<>();

    @Override
    public Set<ServiceModel> getServicesInfo() {
        return new HashSet<>(services.values());
    }

    @Override
    public void addService(ServiceModel serviceModel) {
        services.put(serviceModel.serviceId, serviceModel);
    }

    @Override
    public void rewriteService(ServiceModel newModel) {
        if (services.containsKey(newModel.serviceId)) {
            services.replace(newModel.serviceId, newModel);
        }
        else
            throw new RuntimeException(String.format("Servie does not exist: uuid: %s", newModel.serviceId.toString()));
    }
}
