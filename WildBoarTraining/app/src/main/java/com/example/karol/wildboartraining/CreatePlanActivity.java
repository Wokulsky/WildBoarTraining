package com.example.karol.wildboartraining;
import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;


public class CreatePlanActivity extends AppCompatActivity implements View.OnClickListener {

    //
    //  Lasciate ogne speranza, voi ch’intrate.
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "tworzysz Plan!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        View logOutButton = findViewById(R.id.logout_button);
        logOutButton.setOnClickListener(this);

        if (!ClientConnection.IsLogged()){
            AlertDialog alertDialog = new AlertDialog.Builder(CreatePlanActivity.this).create();
            alertDialog.setTitle("Połączenie");
            alertDialog.setMessage("Nie jesteś zalogowany z serwerem!");

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Logowanie",new DialogInterface.OnClickListener() {

                //
                //  Kliknołeś w Zaloguj, więc wyświeetla się altertbutton związany z logowaniem
                //
                public void onClick(DialogInterface dialog, int which) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(CreatePlanActivity.this).create();
                    // Get the layout inflater
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    alertDialog.setView(inflater.inflate(R.layout.dialog_signin, null));

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Logowanie", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //PANEL ZALOGOWANIA BIERZEMY STRINGI OD UŻYTKOWNIKA
                            String messageType = "LoginRequest";
                            List<String> parameters  = new ArrayList<String>();

                            EditText editText = (EditText)alertDialog.findViewById(R.id.login_in);
                            //LOGIN index 0
                            parameters.add(editText.getText().toString());
                            editText = (EditText)alertDialog.findViewById(R.id.password_in);
                            //PASSWORD index 1

                            parameters.add(editText.getText().toString());

                            //TU JUŻ PO STAREMU...
                            boolean flag = true;
                            for (String parameter: parameters){
                                if (!parameter.matches("[a-zA-Z0-9]*") || parameter.equals("") || parameter.equals(" ")){
                                    flag =false;
                                    break;
                                }
                            }

                            if ( flag ){
                                InputStream keyin = CreatePlanActivity.this.getResources().openRawResource(R.raw.testkeysore);
                                ClientConnection client = new ClientConnection(keyin,"LoginRequest",parameters);
                                String connectionResult = client.runConnection();

                                if (connectionResult.equals("LoginRequest") && client.IsLogged()) {
                                    Toast.makeText(CreatePlanActivity.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (!client.IsLogged())
                                        Toast.makeText(CreatePlanActivity.this, "Nie ma Cię w bazie", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(CreatePlanActivity.this, "Błąd", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }else Toast.makeText(CreatePlanActivity.this, "Źle wprowadzone dane", Toast.LENGTH_LONG).show();

                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Anuluj", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialog.show();
                }

            });

            //---------------------KONIEC PRZYCISKU "ZALOGUJ"---------------------


            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Rejestracja",new DialogInterface.OnClickListener() {

                //
                //  Kliknołeś w Rejestracje, więc tworzymy alterDialog dla Rejestracji
                //
                public void onClick(DialogInterface dialog, int which){

                    final AlertDialog alertDialogRegister = new AlertDialog.Builder(CreatePlanActivity.this).create();
                    // Get the layout inflater
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    alertDialogRegister.setView(inflater.inflate(R.layout.dialog_signup, null));

                    alertDialogRegister.setButton(AlertDialog.BUTTON_POSITIVE, "Rejestruj", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            //PANEL REJESTRACJI UŻYTKOWINKA

                            String messageType = "RegisterNewClient";
                            List<String> parameters  = new ArrayList<String>();

                            EditText editText = (EditText)alertDialogRegister.findViewById(R.id.login_up);
                            //LOGIN index 0
                            parameters.add( editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.password_up);
                            //PASSWORD index 1
                            parameters.add( editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.first_name);

                            parameters.add( editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.last_name);

                            parameters.add( editText.getText().toString() );

                            //Toast.makeText(CreatePlanActivity.this, parameters.get(0) + " " + parameters.get(3), Toast.LENGTH_SHORT).show();
                            boolean flag = true;
                            for (String parameter: parameters){
                                if (!parameter.matches("[a-zA-Z0-9]*") || parameter.equals("") || parameter.equals(" ")){

                                    flag = false;
                                    break;
                                }
                            }
                            if ( flag ){
                                InputStream keyin = CreatePlanActivity.this.getResources().openRawResource(R.raw.testkeysore);
                                ClientConnection client = new ClientConnection(keyin,"RegisterNewClient",parameters);
                                String connectionResult = client.runConnection();

                                if (connectionResult.equals("RegisterNewClient")) {
                                    Toast.makeText(CreatePlanActivity.this, "Rejestracja Udana", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CreatePlanActivity.this, "Błąd", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }else Toast.makeText(CreatePlanActivity.this, "Źle wprowadzone dane", Toast.LENGTH_LONG).show();



                        }
                    });

                    alertDialogRegister.setButton(AlertDialog.BUTTON_NEGATIVE, "Anuluj", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    alertDialogRegister.show();


                }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button:
                ClientConnection.closeConnection();
                Toast.makeText(CreatePlanActivity.this, "Wylogowano", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

}