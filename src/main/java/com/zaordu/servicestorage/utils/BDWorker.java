package com.zaordu.servicestorage.utils;
import com.zaordu.servicestorage.controllers.ServicesStatusController;
import com.zaordu.servicestorage.models.ServiceModel;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BDWorker {
    private static final String ConnectionStrDB = "jdbc:sqlite:services.db";

    private static BDWorker instance = null;

    public static synchronized BDWorker getInstance(){
        try {
            if (instance == null)
                    instance = new BDWorker();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return instance;
    }

    private Connection connection;

    public BDWorker() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(ConnectionStrDB);
    }

    public List<String> getAllServices() {
        try (Statement statement = this.connection.createStatement()) {
            List<String> services = new ArrayList<String>();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, link, work_status FROM services_info");
            while (resultSet.next()) {
                services.add(String.format("%s %s %s %s",
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("link"),
                        resultSet.getBoolean("work_status")));
            }
            return services;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addService(ServiceModel service) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO services_info(`id`, `name`, `link`, `work_status`)" +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, service.serviceId);
            statement.setObject(2, service.serviceName);
            statement.setObject(3, service.link);
            statement.setObject(4, service.serviceStatus);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createService(ServicesStatusController serviceController){
        var servise = serviceController.addRandomService();
    }
}
