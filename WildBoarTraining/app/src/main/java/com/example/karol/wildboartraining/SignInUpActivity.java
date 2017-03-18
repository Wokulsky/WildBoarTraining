package com.example.karol.wildboartraining;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignInUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);
        AlertDialog alertDialog = new AlertDialog.Builder(SignInUpActivity.this).create();
        alertDialog.setTitle("Połączenie");
        alertDialog.setMessage("Nie jesteś zalogowany z serwerem!");

        //  Kod MAC urządzenia
        //WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //WifiInfo info = manager.getConnectionInfo();
        //final String macAddress = info.getMacAddress();

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
                Intent intent = new Intent(getBaseContext(),SignUpSurveyActivity.class);
                startActivity(intent);
            }

        });

        alertDialog.show();

    }

}
