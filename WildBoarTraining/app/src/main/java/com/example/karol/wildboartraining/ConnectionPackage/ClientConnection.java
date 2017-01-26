package com.example.karol.wildboartraining.ConnectionPackage;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.List;

public class ClientConnection {

    //UWAGA!! Zmienna volatile jest po to aby ułatwić
    //        synchronizację procesów ( funkcji runConnectionoraz doInBackground)
    //
    //Pytamy czy udało się logowanie - czy klient faktycznie jest w bazie i czy połączenie jest ok
    private static volatile boolean isLogged=false;
    //Flaga czy udało nam się odpowienio połąćzyć z serverm
    private static volatile boolean isConnected = false;

    private static volatile String result = "ERROR";
    private SSLConnector sslConnector;
    private InputStream keyin;

    private String MessageType;
    private String messageToSend;
    private List<String> parameters;
    public ClientConnection(InputStream keyin, String messageType, List<String> parameters){
        this.keyin = keyin;
        this.MessageType = messageType;
        this.parameters = parameters;
    }

    public static boolean IsLogged(){
        return isLogged;
    }
    public static boolean IsConnected(){
        return  isConnected;
    }
    public static void closeConnection() {
            isConnected = false;
            isLogged = false;
            result = "ERROR";

    }
    public String runConnection( ){

        new ConnectionTask().execute();

        while ( !IsConnected() ){
            //Czekamy aż się połączy! - czekamy aż falaga isConnected będzie zwrócona na true
        }

        return result;
    }


    private class ConnectionTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

                sslConnector = SSLConnector.getInstance(keyin);

                switch(MessageType){

                    case ("LoginRequest"):
                        messageToSend = JSONMessage.jsonLogin( parameters.get(0), parameters.get(1));
                        break;
                    case ("RegisterNewClient"):
                        messageToSend = JSONMessage.jsonRegister(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3));
                        break;
                    default:
                        messageToSend = JSONMessage.jsonGetData();
                        break;
                }
                sslConnector.sendMessageToServer(messageToSend);
                JSONObject JSONanswer = sslConnector.getMessageFromServer();
                Log.d ("TAG_CONN",JSONanswer.toString());
                switch(JSONanswer.getString("message_type")){

                    case ("LoginRequest"):
                        isLogged = JSONanswer.getBoolean("islogged");
                        result = "LoginRequest";
                        break;
                    case ("RegisterNewClient"):
                        isLogged = true;
                        result = "RegisterNewClient";
                        break;
                    case("GetBasicData"):
                        result = "GetBasicData";
                        break;
                    default://Mamy Error!
                        isLogged = false;
                        result ="ERROR";
                        break;
                }

                //Zwalniamy semafor, możemy przejść dalej
                isConnected = true;

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}