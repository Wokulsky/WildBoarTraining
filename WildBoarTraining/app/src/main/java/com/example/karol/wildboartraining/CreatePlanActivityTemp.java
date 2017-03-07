package com.example.karol.wildboartraining;
import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;

import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.InputStream;
import java.util.HashMap;


public class CreatePlanActivityTemp extends AppCompatActivity implements View.OnClickListener {

    //
    //  Lasciate ogne speranza, voi ch’intrate.
    //
    /*
        "On otrze z ich oczu wszelką łzę.
            Nie będzie już odtąd bałaganu ani żadnego smutku, ani narzekań, ani utrapienia,
            albowiem pierwsze wersje kodu przeminą. "

     */

    //TODO
    //Przerobić z AlertDialogów na Activity, przynajmniej część związaną z Wyborem między rejestracją a logowaniem
    //Resztę w sumie też

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "tworzysz Plan!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        View logOutButton = findViewById(R.id.survey_button);
        logOutButton.setOnClickListener(this);


        if (!ClientConnection.IsLogged()){
            /*
                KOMENTARZ DO TEGO CO SIĘ TUTAJ DZIEJE
                Tworzymy alter dialog z dwoma przyciskami - zaloguj i rejestruj
                Po wcisnięciu odpowiedniego przycisku zaloguj lub rejestruj pojawia się kolejny alter dialog
                z formularzem rejestracji lub logowaniem
             */

            //Inicjacja alter dialog//
            AlertDialog alertDialog = new AlertDialog.Builder(CreatePlanActivityTemp.this).create();
            alertDialog.setTitle("Połączenie");
            alertDialog.setMessage("Nie jesteś zalogowany z serwerem!");

            WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            //final String macAddress = info.getMacAddress();
            final String macAddress = "123";
            //---------------------------------------PRZYCISK--ZALOGUJ------------------------------
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Logowanie",new DialogInterface.OnClickListener() {

                //
                //  Kliknołeś w Zaloguj, więc wyświeetla się alertdialog związany z logowaniem
                //
                public void onClick(DialogInterface dialog, int which) {

                    final AlertDialog alertDialog = new AlertDialog.Builder(CreatePlanActivityTemp.this).create();

                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

                    alertDialog.setView(inflater.inflate(R.layout.dialog_signin, null));

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Logowanie", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            //PANEL ZALOGOWANIA BIERZEMY STRINGI OD UŻYTKOWNIKA
                            String messageType = "LoginRequest";
                            HashMap<String,String> parameters = new HashMap<>();

                            EditText editText = (EditText)alertDialog.findViewById(R.id.login_in);

                            parameters.put("login",editText.getText().toString());

                            editText = (EditText)alertDialog.findViewById(R.id.password_in);

                            parameters.put("password",editText.getText().toString());

                            //TU JUŻ PO STAREMU...
                            boolean flag = true;
                            /*for (String parameter: parameters){
                                if (!parameter.matches("[a-zA-Z0-9]*") || parameter.equals("") || parameter.equals(" ")){
                                    flag =false;
                                    break;
                                }
                            }*/
                            parameters.put("device_id",macAddress);
                            //Jeżeli danę są dobrze wprowadzone to
                            if ( flag ){

                                InputStream keyin = CreatePlanActivityTemp.this.getResources().openRawResource(R.raw.testkeysore);
                                final ClientConnection client = new ClientConnection(keyin,"LoginRequest",parameters);
                                String connectionResult = client.runConnection();
                                Log.d("Tag-CPA",connectionResult);
                                if (connectionResult.equals("LoginRequest") && client.IsLogged()) {
                                    Toast.makeText(CreatePlanActivityTemp.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (connectionResult.equals("enter verify code") ){
                                        final AlertDialog alertVerifyDialog = new AlertDialog.Builder(CreatePlanActivityTemp.this).create();
                                        // Get the layout inflater
                                        LayoutInflater inflaterVerify = LayoutInflater.from(getApplicationContext());
                                        alertVerifyDialog.setView(inflaterVerify.inflate(R.layout.verify_alert, null));
                                        final HashMap<String,String> verifyParametrs = new HashMap<>();
                                        verifyParametrs.put("login",parameters.get(0));
                                        verifyParametrs.put("password",parameters.get(1));
                                        verifyParametrs.put("id_device",macAddress);


                                        alertVerifyDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Wyślij", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                EditText editTextVerify = (EditText)alertVerifyDialog.findViewById(R.id.verify);
                                                verifyParametrs.put("verify_code",editTextVerify.getText().toString());

                                                String ans = client.verifyClient(verifyParametrs);

                                                if ( ans.equals("AddDevice")){
                                                    Toast.makeText(CreatePlanActivityTemp.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(CreatePlanActivityTemp.this, "Błędna weryfikacja", Toast.LENGTH_SHORT).show();
                                                }
                                                finish();
                                            }
                                        });
                                        alertVerifyDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Anuluj", new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        });
                                        alertVerifyDialog.show();
                                    }
                                    else {
                                        Toast.makeText(CreatePlanActivityTemp.this, "Błąd Logowania", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                }
                            }else Toast.makeText(CreatePlanActivityTemp.this, "Źle wprowadzone dane", Toast.LENGTH_LONG).show();

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


            //---------------------PRYCISK--REJESTRUJ-----------------------------
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Rejestracja",new DialogInterface.OnClickListener() {

                //
                //  Kliknołeś w Rejestracje, więc tworzymy alterDialog dla Rejestracji
                //
                public void onClick(DialogInterface dialog, int which){

                    final AlertDialog alertDialogRegister = new AlertDialog.Builder(CreatePlanActivityTemp.this).create();
                    // Get the layout inflater
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    alertDialogRegister.setView(inflater.inflate(R.layout.dialog_signup, null));

                    alertDialogRegister.setButton(AlertDialog.BUTTON_POSITIVE, "Rejestruj", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            //PANEL REJESTRACJI UŻYTKOWINKA

                            String messageType = "RegisterNewClient";
                            HashMap<String,String> parameters = new HashMap<>();

                            EditText editText = (EditText)alertDialogRegister.findViewById(R.id.login_up);

                            parameters.put("login", editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.password_up);

                            parameters.put("password", editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.first_name);

                            parameters.put("name", editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.last_name);

                            parameters.put( "lastname",editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.phone_number);

                            parameters.put("PHONE", editText.getText().toString() );

                            editText = (EditText)alertDialogRegister.findViewById(R.id.email);

                            parameters.put("EMAIL", editText.getText().toString() );

                            //parameters.add(macAddress);
                            final CheckBox smsVerifyBox = (CheckBox) alertDialogRegister.findViewById(R.id.VerifySmsBox);
                            final CheckBox emailVerifyBox = (CheckBox) alertDialogRegister.findViewById(R.id.VerifyEmailBox);
                            String verify = "SMS";
                            if (emailVerifyBox.isChecked() ){
                                emailVerifyBox.setChecked(true);
                                smsVerifyBox.setChecked(false);
                                verify = "EMAIL";
                                Toast.makeText(CreatePlanActivityTemp.this, verify, Toast.LENGTH_LONG).show();
                            }

                            if (smsVerifyBox.isChecked() ){
                                emailVerifyBox.setChecked(false);
                                smsVerifyBox.setChecked(true);
                                verify ="SMS";
                                Toast.makeText(CreatePlanActivityTemp.this, verify, Toast.LENGTH_LONG).show();
                            }

                            parameters.put("verify_way",verify);

                            InputStream keyin = CreatePlanActivityTemp.this.getResources().openRawResource(R.raw.testkeysore);
                            ClientConnection client = new ClientConnection(keyin,"RegisterNewClient",parameters);
                            String connectionResult = client.runConnection();

                            if (connectionResult.equals("RegisterNewClient")) {
                                Toast.makeText(CreatePlanActivityTemp.this, "Rejestracja Udana", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(CreatePlanActivityTemp.this, "Błąd", Toast.LENGTH_LONG).show();
                                finish();
                            }
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
            //KONIEC GŁÓWNEGO ALTERDIALOGU
        }

        /*TU POWINIEN BYĆ KOD Z FORMOWANIEM PLANU DLA UŻTKOWNIKA
        //TODO
        */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.survey_button:
                ClientConnection.closeConnection();
                Toast.makeText(CreatePlanActivityTemp.this, "Wylogowano", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

}