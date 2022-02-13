package com.zaordu.servicestorage.demons;

import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.utils.BDWorker;
import com.zaordu.servicestorage.utils.HTTPServiceChecker;
import com.zaordu.servicestorage.utils.ServiceStatus;

import java.util.Arrays;

public class UpdateDB implements Runnable{
    private final BDWorker worker = BDWorker.getInstance();
    private final ServiceHandler serviceHandler = com.zaordu.servicestorage.utils.ServiceHandler.getInstance();
    private final HTTPServiceChecker checker = new HTTPServiceChecker();

    @Override
    public void run() {
        while(true) {
            try {
                loadDataFromDB();
                checkServices();
                loadDataToDB();
                updateStatusDB();
            }catch (RuntimeException e){
                System.out.format(
                        "Some error was risen:\n %s \n Stack trace: %s",
                        e.getMessage(),
                        String.join("\n", Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new))
                );
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkServices(){
        for(var service : serviceHandler.getServicesInfo()){
            service.serviceStatus = checker.check(service.link);
        }
    }

    private void updateStatusDB(){
        for(var service : serviceHandler.getServicesInfo()){
            if(service.serviceStatus == ServiceStatus.RUNNING)
                activateService(service.serviceId.toString());
            else
                deactivateService(service.serviceId.toString());
        }
    }

    public void deactivateService(String id){
        worker.deactivateService(id);
    }

    public void activateService(String id){
        worker.activateService(id);
    }

    private void loadDataFromDB(){
        serviceHandler.setServices(worker.getAllServices());
    }

    private void loadDataToDB(){
        for(var service : serviceHandler.getServicesInfo()) {
            if (!worker.containService(service.serviceId.toString())) {
                worker.addService(service);
            }
        }
    }
}
