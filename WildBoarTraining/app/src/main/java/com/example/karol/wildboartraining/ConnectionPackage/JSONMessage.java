package com.example.karol.wildboartraining.ConnectionPackage;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONMessage {
    static String jsonLogin( List<String> parameters ){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", "LoginRequest");
        data.put("login", parameters.get(0));
        data.put("password", parameters.get(1));
        data.put("device_id",parameters.get(2));
        return new JSONObject(data).toString();
    }

    static String jsonRegister( List<String> parameters ){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", "RegisterNewClient");
        data.put("login", parameters.get(0));
        data.put("password", parameters.get(1));
        data.put("lastname", parameters.get(2));
        data.put("name", parameters.get(3));
        data.put("PHONE",parameters.get(4));
        data.put("EMAIL",parameters.get(5));
        data.put("verify_way",parameters.get(6));
        return new JSONObject(data).toString();
    }
    static String jsonGetData(){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("message_type", "getData");
        return new JSONObject(data).toString();
    }
    static String jsonAddDevice( List<String> parameters ){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("login", parameters.get(0));
        data.put("password", parameters.get(1));
        data.put("verify_code", parameters.get(2));
        return new JSONObject(data).toString();
    }
}

