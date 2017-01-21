package com.example.karol.wildboartraining.ConnectionPackage;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.SSLSocket;

public class ClientConnection {
    public static volatile boolean isLogged=false;
    public static volatile boolean isConnected = false;
    private static SSLConnector sslConnector;
    public static SSLSocket sslsocket;
    private static KeyStore ks;
    private static InputStream keyin;
    private static char[] keystorepass = "dzikidzik".toCharArray();
    public static String Login ="admin";
    public static String Password="haslo";
    public ClientConnection(InputStream keyin){
        this.keyin = keyin;
    }
    public static boolean IsLogged(){
        return isLogged;
    }
    public static boolean IsConnected(){ return  isConnected; }

    public boolean runConnection(){
        Log.d("TAG-Connection","Przed ConnectionTask");
        new ConnectionTask().execute();
        Log.d("TAG-Connection","Po ConnectionTask");
        while ( !IsConnected() ){
            //Czekamy aż się połączy!
        }
        Log.d("TAG-Connection","Doczekalem sie");
        return isLogged;
    }
    private class ConnectionTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                KeyStore ks = KeyStore.getInstance("PKCS12");
                ks.load(keyin,keystorepass);
                org.apache.http.conn.ssl.SSLSocketFactory socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(ks);
                socketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                sslsocket = (SSLSocket)
                        socketFactory.createSocket(new Socket("185.157.80.59",7632), "185.157.80.59", 7632, false);
                Log.d("TAG_Task","Po CreateSocket");
                sslsocket.startHandshake();
                Log.d("TAG_Task","Po handshake");
                isConnected = true;
                Log.d("TAG_Task","JuPI!");
                Map<String, String> data = new LinkedHashMap<>();
                data.put("message_type", "LoginRequest");
                data.put("login", Login);
                data.put("password", Password);
                JSONObject message = new JSONObject(data);
                String messageString = message.toString();
                PrintWriter pw = null;
                pw = new PrintWriter(sslsocket.getOutputStream());
                pw.write(messageString);
                pw.write("\n");
                pw.flush();

                BufferedReader br = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));
                String serverAnswer = br.readLine();

                JSONObject JSONanswer = new JSONObject(serverAnswer);
                String wynik = JSONanswer.getString("message_type");
                if(!wynik.equals("LoginRequest"))
                    isLogged = false;
                else isLogged = JSONanswer.getBoolean("islogged");

                isConnected = true;

            } catch (KeyStoreException e) {
                Log.d("TAG-Connection", e.toString());
            } catch (CertificateException e) {
                Log.d("TAG-Connection", e.toString());
            } catch (NoSuchAlgorithmException e) {
                Log.d("TAG-Connection", e.toString());
            } catch (IOException e) {
                Log.d("TAG-Connection", e.toString());
            } catch (UnrecoverableKeyException e) {
                Log.d("TAG-Connection", e.toString());
            } catch (KeyManagementException e) {
                Log.d("TAG-Connection", e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
