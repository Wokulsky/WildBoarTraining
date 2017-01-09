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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClientSSLConnection {

    private EditText mText;
    private Button mSend;
    private TextView mResponse;
    private EditText mIPaddress;
    private EditText mPort;

    // port to use
    private String ip_address;
    private int port;
    private SSLSocket socket = null;
    private BufferedWriter out = null;
    private BufferedReader in = null;
    private final String TAG;
    private char keystorepass[];
    private char keypassword[];
    private static ClientSSLConnection instance = null;
    private static SSLSocket sslsocket ;
    public ClientSSLConnection() {
        port = 9998;
        TAG = "TAG";
        keystorepass = "key12345".toCharArray();
        keypassword = "12345key".toCharArray();
        ip_address = "185.157.80.59";
    }

    public void runConnection(View v) {
        Log.d(TAG, "makes it to here");
        try {
            KeyStore ks = KeyStore.getInstance("BKS");
            InputStream keyin = v.getResources().openRawResource(R.raw.testkey);
            ks.load(keyin, keystorepass);
            SSLSocketFactory socketFactory = new SSLSocketFactory(ks);
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            socket = (SSLSocket) socketFactory.createSocket(new Socket(ip_address, port), ip_address, port, false);
            socket.startHandshake();

            printServerCertificate(socket);
            printSocketInfo(socket);

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp = "absa";
            chat(temp);
        } catch (UnknownHostException e) {
            Toast.makeText(v.getContext(), "Unknown host", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Unknown host");
        } catch (IOException e) {
            Toast.makeText(v.getContext(), "No I/O", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "No I/O");
            e.printStackTrace();
        } catch (KeyStoreException e) {
            Toast.makeText(v.getContext(), "Keystore ks error", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Keystore ks error");
            //System.exit(-1);
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(v.getContext(), "No such algorithm for ks.load", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "No such algorithm for ks.load");
            e.printStackTrace();
            //System.exit(-1);
        } catch (CertificateException e) {
            Toast.makeText(v.getContext(), "certificate missing", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "certificate missing");
            e.printStackTrace();
            //System.exit(-1);
        } catch (UnrecoverableKeyException e) {
            Toast.makeText(v.getContext(), "UnrecoverableKeyException", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "unrecoverableKeyException");
            e.printStackTrace();
            //System.exit(-1);
        } catch (KeyManagementException e) {
            Toast.makeText(v.getContext(), "KeyManagementException", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "key management exception");
            e.printStackTrace();
        }
    }

    private void printServerCertificate(SSLSocket socket) {
        try {
            Certificate[] serverCerts =
                    socket.getSession().getPeerCertificates();
            for (int i = 0; i < serverCerts.length; i++) {
                Certificate myCert = serverCerts[i];
                Log.d(TAG, "====Certificate:" + (i + 1) + "====");
                Log.d(TAG, "-Public Key-\n" + myCert.getPublicKey());
                Log.d(TAG, "-Certificate Type-\n " + myCert.getType());

                System.out.println();
            }
        } catch (SSLPeerUnverifiedException e) {
            Log.d(TAG, "Could not verify peer");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void printSocketInfo(SSLSocket s) {
        Log.d(TAG, "Socket class: " + s.getClass());
        Log.d(TAG, "   Remote address = "
                + s.getInetAddress().toString());
        Log.d(TAG, "   Remote port = " + s.getPort());
        Log.d(TAG, "   Local socket address = "
                + s.getLocalSocketAddress().toString());
        Log.d(TAG, "   Local address = "
                + s.getLocalAddress().toString());
        Log.d(TAG, "   Local port = " + s.getLocalPort());
        Log.d(TAG, "   Need client authentication = "
                + s.getNeedClientAuth());
        SSLSession ss = s.getSession();
        Log.d(TAG, "   Cipher suite = " + ss.getCipherSuite());
        Log.d(TAG, "   Protocol = " + ss.getProtocol());
    }

    private void chat(String temp) {
        String message = temp;
        String line = "";
        // send id of the device to match with the image
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e2) {
            Log.d(TAG, "Read failed");
            System.exit(1);
        }
        // receive a ready command from the server
        try {
            line = in.readLine();
            mResponse.setText("SERVER SAID: " + line);
            //Log.d(TAG,line);
        } catch (IOException e1) {
            Log.d(TAG, "Read failed");
            System.exit(1);
        }
    }
}
