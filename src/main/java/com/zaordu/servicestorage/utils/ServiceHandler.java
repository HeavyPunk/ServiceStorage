package com.zaordu.servicestorage.utils;

import com.zaordu.servicestorage.models.ServiceModel;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TODO:Добавить локкеры на services
public class ServiceHandler implements com.zaordu.servicestorage.abstractions.ServiceHandler {
    private HashMap<UUID, ServiceModel> services = new HashMap<>();

    private static ServiceHandler instance = null;

    private final Lock lock = new ReentrantLock();

    public static ServiceHandler getInstance(){
        if (instance == null)
            instance = new ServiceHandler();
        return instance;
    }

    @Override
    public Set<ServiceModel> getServicesInfo() {
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                return new HashSet<>(services.values());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return new HashSet<>();
    }

    @Override
    public void setServices(List<ServiceModel> servicesList){
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                services = new HashMap<UUID, ServiceModel>();
                for (var service : servicesList) {
                    services.put(service.serviceId, service);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
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
