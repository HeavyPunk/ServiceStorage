package com.zaordu.servicestorage.models;

import com.zaordu.servicestorage.utils.ServiceStatus;

import java.util.UUID;

public class ServiceModel {
    public UUID serviceId;
    public String serviceName;
    public ServiceStatus serviceStatus;
}
