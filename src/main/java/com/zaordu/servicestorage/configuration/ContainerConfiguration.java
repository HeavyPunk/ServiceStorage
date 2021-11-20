package com.zaordu.servicestorage.configuration;

import com.zaordu.servicestorage.abstractions.JsonManager;
import com.zaordu.servicestorage.abstractions.ServiceHandler;
import com.zaordu.servicestorage.utils.DefaultJsonManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContainerConfiguration {
    @Bean
    public ServiceHandler getServiceHandler(){
        return new com.zaordu.servicestorage.utils.ServiceHandler();
    }
    @Bean
    public JsonManager jsonManager(){
        return new DefaultJsonManager();
    }
}
