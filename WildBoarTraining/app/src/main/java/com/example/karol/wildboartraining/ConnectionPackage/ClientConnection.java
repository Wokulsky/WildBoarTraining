package com.example.karol.wildboartraining.ConnectionPackage;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;

public class ClientConnection {

    //UWAGA!! Zmienna volatile jest po to aby ułatwić
    //        synchronizację procesów ( funkcji runConnectionoraz doInBackground)
    //
    //Pytamy czy udało się logowanie - czy klient faktycznie jest w bazie i czy połączenie jest ok
    private static volatile boolean isLogged=false;
    //Flaga czy udało nam się odpowienio połąćzyć z serverm
    private static volatile boolean isConnected = false;
    private static SSLConnector sslConnector;
    private static InputStream keyin;

    private static String Login ="admin";
    private static String Password="haslo";

    public ClientConnection(InputStream keyin){
        this.keyin = keyin;
    }
    public static boolean IsLogged(){
        return isLogged;
    }
    public static boolean IsConnected(){ return  isConnected; }

    public boolean runConnection(){

        new ConnectionTask().execute();

        while ( !IsConnected() ){
            //Czekamy aż się połączy! - czekamy aż falaga isConnected będzie zwrócona na true
        }

        return isLogged;
    }
    private class ConnectionTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

                sslConnector = SSLConnector.getInstance(keyin);

                String messageToSend = JSONMessage.jsonLogin("LoginRequest", Login, Password);

                PrintWriter pw = new PrintWriter(sslConnector.sslsocket.getOutputStream());
                pw.write(messageToSend);
                pw.write("\n");
                pw.flush();

                BufferedReader br = new BufferedReader(new InputStreamReader(sslConnector.sslsocket.getInputStream()));
                String serverAnswer = br.readLine();
                JSONObject JSONanswer = new JSONObject(serverAnswer);
                String wynik = JSONanswer.getString("message_type");
                Log.d("TAG_CONN_MESS_FROM_SERV",wynik+" "+JSONanswer.getBoolean("islogged"));
                if (!wynik.equals("LoginRequest")) {
                    isLogged = false;
                } else isLogged = JSONanswer.getBoolean("islogged");

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