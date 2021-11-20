package com.zaordu.servicestorage.utils;

import com.google.gson.GsonBuilder;
import com.zaordu.servicestorage.abstractions.JsonManager;

public class DefaultJsonManager implements JsonManager {

    @Override
    public String serialise(Object obj) {
        var serializer = new GsonBuilder().setPrettyPrinting().create();
        return serializer.toJson(obj);
    }
}
