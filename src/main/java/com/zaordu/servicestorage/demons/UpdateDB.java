package com.zaordu.servicestorage.demons;

import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.configuration.ContainerConfiguration;
import com.zaordu.servicestorage.utils.BDWorker;
import com.zaordu.servicestorage.utils.ServiceStatus;

public class UpdateDB implements Runnable{
    private final BDWorker worker = BDWorker.getInstance();
    private final ServiceHandler serviceHandler = com.zaordu.servicestorage.utils.ServiceHandler.getInstance();

    @Override
    public void run() {
        while(true) {
            for(var service : serviceHandler.getServicesInfo()){
                if(service.serviceStatus == ServiceStatus.RUNNING)
                    activateService(service.serviceId.toString());
                else
                    deactivateService(service.serviceId.toString());
            }
        }
    }

    public void deactivateService(String id){
        worker.deactivateService(id);
    }

    public void activateService(String id){
        worker.activateService(id);
    }
}
