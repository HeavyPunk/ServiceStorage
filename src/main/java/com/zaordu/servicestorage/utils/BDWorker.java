package com.zaordu.servicestorage.utils;
import java.sql.SQLException;

public class BDWorker {
    private static final String ConnectionStrDB = "jdbc:sqlite:services.db";

    private static BDWorker instance = null;

    public static synchronized BDWorker getInstance() throws SQLException {
        if (instance == null)
            instance = new BDWorker();
        return instance;
    }

    public static void createService(){

    }
}
