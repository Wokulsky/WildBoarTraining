package com.example.karol.wildboartraining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.security.cert.Certificate;

import org.apache.http.conn.ssl.SSLSocketFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CreatePlanActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "TAG";
    private char keystorepass[] = "key12345".toCharArray();
    private char keypassword[] = "12345key".toCharArray();
    private BufferedWriter out = null;
    private BufferedReader in = null;
    private SSLSocket socket = null;
    private TextView mResponse;

    ClientSSLConnection client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        View connsectionButton = findViewById(R.id.connection_button);
        connsectionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v.getId() == R.id.connection_button) {
                client = new ClientSSLConnection();
                client.runConnection(v);
            }
        }
    }

