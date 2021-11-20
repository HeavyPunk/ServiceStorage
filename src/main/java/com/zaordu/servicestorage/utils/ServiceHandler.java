package com.zaordu.servicestorage.utils;

import com.zaordu.servicestorage.models.ServiceModel;

import java.sql.SQLException;
import java.util.*;

//TODO:Добавить локкеры на services
public class ServiceHandler implements com.zaordu.servicestorage.abstractions.ServiceHandler {
    private HashMap<UUID, ServiceModel> services = new HashMap<>();

    private static ServiceHandler instance = null;

    public static ServiceHandler getInstance(){
        if (instance == null)
            instance = new ServiceHandler();
        return instance;
    }

    @Override
    public Set<ServiceModel> getServicesInfo() {
        return new HashSet<>(services.values());
    }

    @Override
    public void setServices(List<ServiceModel> servicesList){
        services = new HashMap<UUID, ServiceModel>();
        for(var service : servicesList){
            services.put(service.serviceId, service);
        }
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

    @Override
    public ServiceModel getServiceInfo(UUID id) {
        return services.getOrDefault(id, null);
    }
}
