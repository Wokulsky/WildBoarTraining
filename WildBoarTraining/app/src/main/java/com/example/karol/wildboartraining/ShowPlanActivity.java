package com.example.karol.wildboartraining;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

public class ShowPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan);

        AlertDialog alertDialog = new AlertDialog.Builder(ShowPlanActivity.this).create();
        alertDialog.setTitle("Połączenie");
        alertDialog.setMessage("Nie jesteś zalogowany z serwerem!");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Logowanie",new DialogInterface.OnClickListener() {

            //
            //  Kliknołeś w Zaloguj, więc wyświeetla się altertbutton związany z logowaniem
            //
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog alertDialog = new AlertDialog.Builder(ShowPlanActivity.this).create();
                // Get the layout inflater
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                alertDialog.setView(inflater.inflate(R.layout.dialog_signin, null));

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Logowanie", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ShowPlanActivity.this, "ZALOGOWANIE", Toast.LENGTH_SHORT).show();

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

                AlertDialog alertDialog = new AlertDialog.Builder(ShowPlanActivity.this).create();
                // Get the layout inflater
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                alertDialog.setView(inflater.inflate(R.layout.dialog_signup, null));

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Rejestruj", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ShowPlanActivity.this, "Rejestrowanie", Toast.LENGTH_SHORT).show();

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
        /*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Anuluj",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which){
                finish();
            }
        });*/

        alertDialog.show();
    }
}
