package com.example.karol.wildboartraining.ConnectionPackage;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import static com.example.karol.wildboartraining.ConnectionPackage.SSLConnector.sslsocket;


public class ClientConnection {
    public static boolean isLogged=false;
    private static SSLConnector sslConnector;
    private static SSLSocket sslsocket2;

    public ClientConnection(){
        String currentDir = System.getProperty("user.dir")+"/testkeysore.p12";
        System.setProperty("javax.net.ssl.keyStore",currentDir);
        System.setProperty("javax.net.ssl.keyStorePassword","dzikidzik");
        System.setProperty("javax.net.ssl.keyStoreType","PKCS12");
        System.setProperty("javax.net.ssl.trustStore",currentDir);
        System.setProperty("javax.net.ssl.trustStorePassword","dzikidzik");
        System.setProperty("javax.net.ssl.trustStoreType","PKCS12");
    }
    public static boolean IsLogged(){
        return isLogged;
    }

    public static boolean logIn(String Login,String Password) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("message_type", "LoginRequest");
        data.put("login", Login);
        data.put("password", Password);
        JSONObject message = new JSONObject(data);
        String messageString = message.toString();
        try {

            PrintWriter pw = null;
            pw = new PrintWriter(sslsocket2.getOutputStream());
            pw.write(messageString);
            pw.write("\n");
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(sslsocket2.getInputStream()));
            String serverAnswer = br.readLine();

            JSONObject JSONanswer = new JSONObject(serverAnswer);
            String wynik = JSONanswer.getString("message_type");
            if(!wynik.equals("LoginRequest")){return false;}
            boolean islogged = JSONanswer.getBoolean("islogged");
            isLogged=islogged;
            return  islogged;
        } catch (IOException|JSONException e) {
            System.out.println(e);
            return false;
        }
    }//koniec funkcji logowania

    public boolean runConnection(){
        //sslConnector = SSLConnector.getInstance();
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            sslsocket2 = (SSLSocket)sslsocketfactory.createSocket("localhost", 7632);
            sslsocket2.startHandshake();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(logIn("admin", "admin"));
    }
}
