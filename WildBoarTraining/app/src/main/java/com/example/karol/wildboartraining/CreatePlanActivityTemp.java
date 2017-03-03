package com.example.karol.wildboartraining;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 05.02.2017.
 */

public class CreatePlanActivityTemp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        View logOutButton = findViewById(R.id.logout_button);
        logOutButton.setOnClickListener(this);

        //Tworzę alter dialog z logowaniem/rejestrowaniem

        if (!ClientConnection.IsLogged()) {

            AlertDialog alertDialog = new AlertDialog.Builder(CreatePlanActivityTemp.this).create();
            alertDialog.setTitle("Połączenie");
            alertDialog.setMessage("Nie jesteś zalogowany z serwerem!");

            //  Kod MAC urządzenia
            //WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            //WifiInfo info = manager.getConnectionInfo();
            //final String macAddress = info.getMacAddress();

            final String macAddress = "123";

            //---------------------------------------PRZYCISK--ZALOGUJ------------------------------
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Logowanie", new DialogInterface.OnClickListener() {

                //
                //  Kliknołeś w Zaloguj, więc wyświeetla się alertdialog związany z logowaniem
                //

                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getBaseContext(),SignInActivity.class);
                    startActivity(intent);

                }

            });
            //---------------------PRYCISK--REJESTRUJ-----------------------------
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Rejestracja",new DialogInterface.OnClickListener() {

                //
                //  Kliknołeś w Rejestracje, więc tworzymy alterDialog dla Rejestracji
                //
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
                    startActivity(intent);
                }

            });
        }
    }

    @Override
    public void onClick(View v) {

        String messageType = "LoginRequest";

        List<String> parameters  = new ArrayList<String>();

    }
}
