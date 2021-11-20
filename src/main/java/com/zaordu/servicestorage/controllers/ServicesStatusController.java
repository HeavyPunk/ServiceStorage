package com.zaordu.servicestorage.controllers;

import com.zaordu.servicestorage.abstractions.JsonManager;
import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.models.ServiceModel;
import com.zaordu.servicestorage.utils.ServiceStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/services")
public class ServicesStatusController {
    private final ServiceHandler serviceHandler;
    private final JsonManager jsonManager;

    public ServicesStatusController(ServiceHandler serviceHandler, JsonManager jsonManager){
        this.serviceHandler = serviceHandler;
        this.jsonManager = jsonManager;
    }

    @RequestMapping(value = "/getStatuses", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatuses(){
        var statusesInfo = serviceHandler.getServicesInfo();
        return jsonManager.serialise(statusesInfo);
    }

    @RequestMapping("/addRandomService") //TODO: Не должно быть на проде
    public String addRandomService(){
        var service = new ServiceModel();
        service.serviceId = UUID.randomUUID();
        service.serviceName = "RandomService";
        service.serviceStatus = ServiceStatus.RUNNING;
        serviceHandler.addService(service);
        return "OK";
    }
}
