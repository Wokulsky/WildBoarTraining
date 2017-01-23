package com.example.karol.wildboartraining.ConnectionPackage;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONMessage {
    static String jsonLogin(String messageType,String login, String password ){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", messageType);
        data.put("login", login);
        data.put("password", password);
        return new JSONObject(data).toString();
    }
}

