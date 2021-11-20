package com.zaordu.servicestorage.controllers;

import com.zaordu.servicestorage.abstractions.JsonManager;
import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.models.ServiceModel;
import com.zaordu.servicestorage.utils.BDWorker;
import com.zaordu.servicestorage.utils.ServiceStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/getService", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getService(@RequestParam(name = "id") String id){
        var uuid = UUID.fromString(id);
        var service = serviceHandler.getServiceInfo(uuid);
        if (service == null)
            return new ResponseEntity<String>(jsonManager.serialise("Not found service: " + id) ,HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>(jsonManager.serialise(service), HttpStatus.OK);
    }

    @RequestMapping("/addRandomService") //TODO: Не должно быть на проде
    public String addRandomService(){
        var service = new ServiceModel();
        service.serviceId = UUID.randomUUID();
        service.serviceName = "RandomService";
        service.link = "https://yandex.ru/";
        service.serviceStatus = ServiceStatus.RUNNING;
        bdWorker.addService(service);
        serviceHandler.addService(service);
        return "OK";
    }

    @RequestMapping("/getServices")
    public String getServices(){
        var result = new StringBuilder();
        for(var service: bdWorker.getAllServices()) {
            result.append(service.serviceId).append(" ").append(service.serviceName).append(" ").append(service.serviceStatus.toString()).append(("\n"));        }
        return result.toString();
    }
}
