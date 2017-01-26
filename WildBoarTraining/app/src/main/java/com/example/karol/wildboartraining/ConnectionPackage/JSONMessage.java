package com.example.karol.wildboartraining.ConnectionPackage;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONMessage {
    static String jsonLogin( String login, String password ){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", "LoginRequest");
        data.put("login", login);
        data.put("password", password);
        return new JSONObject(data).toString();
    }

    static String jsonRegister( String login, String password, String lastname, String name ){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", "RegisterNewClient");
        data.put("login", login);
        data.put("password", password);
        data.put("lastname", lastname);
        data.put("name", name);
        return new JSONObject(data).toString();
    }
    static String jsonGetData(){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", "getData");
        return new JSONObject(data).toString();
    }
}

