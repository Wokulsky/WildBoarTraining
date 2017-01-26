package com.example.karol.wildboartraining;
import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;

import android.widget.Toast;

import java.io.InputStream;

import static java.security.AccessController.getContext;


public class CreatePlanActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "tworzysz Plan!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        final Intent intent = new Intent(this,MenuActivity.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(CreatePlanActivity.this);
        LayoutInflater inflater = CreatePlanActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_create_plan, null))
            .setPositiveButton(R.id.log_in_button, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id){
                    //Ładujemy klucz z folderu raw
                    InputStream keyin = CreatePlanActivity.this.getResources().openRawResource(R.raw.testkeysore);
                    ClientConnection client = new ClientConnection(keyin);
                    boolean isConnected;
                    try{
                        isConnected = client.runConnection();
                        Log.d("TAG-Stworz", "Wyszedlem z Connection");
                        Log.d("TAG-CreatePlan", "" + isConnected);
                        if (!isConnected) {
                            Toast.makeText(CreatePlanActivity.this, "Błąd połączenia", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        if (isConnected) {
                            Toast.makeText(CreatePlanActivity.this, "Połączono", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreatePlanActivity.this, "Brak połączenia", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }catch (Exception e){
                        Log.d("TAG-CreatePlan",e.toString());
                    }
                }
            })/*
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            LoginDialogFragment.this.getDialog().cancel();
                        }
                    })*/;
            //return builder.create();

/*
        if (!ClientConnection.IsLogged()){

            //Tworzymy alterDialog
            AlertDialog alertDialog = new AlertDialog.Builder(CreatePlanActivity.this).create();
            alertDialog.setTitle("Logowanie");
            alertDialog.setMessage("Nie jesteś zalogowany");

            //Deklarujemy Pozytywny (Uśmiechnięty) przycisk który służy do połączenia
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Zaloguj",new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog,int which) {
                    // startActivity(intent);//Usunąć !
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Anuluj",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which){
                    finish();
                    //startActivity(intent);

                }
            });
            alertDialog.show();
        }*/
    }

    @Override
    public void onClick(View v) {
    }
}