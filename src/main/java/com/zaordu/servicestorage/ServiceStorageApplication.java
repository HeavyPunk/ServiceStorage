package com.zaordu.servicestorage;

import com.zaordu.servicestorage.demons.UpdateDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceStorageApplication {

    public static void main(String[] args) {
        Thread workWithDB = new Thread(new UpdateDB());
        workWithDB.start();
        SpringApplication.run(ServiceStorageApplication.class, args);
    }

}
