package com.zaordu.servicestorage.controllers;

import com.zaordu.servicestorage.abstractions.JsonManager;
import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.models.ServiceModel;
import com.zaordu.servicestorage.utils.BDWorker;
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
    private final BDWorker bdWorker;

    public ServicesStatusController(ServiceHandler serviceHandler, JsonManager jsonManager, BDWorker bdWorker){
        this.serviceHandler = serviceHandler;
        this.jsonManager = jsonManager;
        this.bdWorker = bdWorker;
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
        service.link = "RandomLink";
        service.serviceStatus = true;
        bdWorker.addService(service);
        serviceHandler.addService(service);
        return "OK";
    }
}
