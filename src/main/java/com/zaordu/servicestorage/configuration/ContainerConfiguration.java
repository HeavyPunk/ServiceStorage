package com.zaordu.servicestorage.configuration;

import com.zaordu.servicestorage.abstractions.JsonManager;
import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.utils.BDWorker;
import com.zaordu.servicestorage.utils.DefaultJsonManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class ContainerConfiguration {
    @Bean
    public ServiceHandler getServiceHandler(){
        return com.zaordu.servicestorage.utils.ServiceHandler.getInstance();
    }
    @Bean
    public JsonManager jsonManager(){
        return new DefaultJsonManager();
    }
    @Bean
    public BDWorker bdWorker() throws SQLException {return new BDWorker();}
}
