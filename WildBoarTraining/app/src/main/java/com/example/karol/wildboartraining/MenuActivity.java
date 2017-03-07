package com.example.karol.wildboartraining;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;

public class MenuActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activiti);

        Log.d("TAG", "tworzysz!!");

        //Tworzę powiązania między przyciskami a działaniem aplikacji
        View createPlanButton = findViewById(R.id.createPlan_button);
        createPlanButton.setOnClickListener(this);

        View showPlanButton = findViewById(R.id.showPlan_lable);
        showPlanButton.setOnClickListener(this);

        View optionButton = findViewById(R.id.option_button);
        optionButton.setOnClickListener(this);

        if (!ClientConnection.IsLogged()) {

            AlertDialog alertDialog = new AlertDialog.Builder(MenuActivity.this).create();
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

            alertDialog.show();
        }

    }

    public void ClickExit(View v){
        finish();
    }
    @Override
    public void onClick(View v){
        Intent intent = null;
        switch(v.getId()){
            case R.id.createPlan_button:
                Log.d("TAG", "kliknoles stworz plan!!");
                intent = new Intent(this,CreatePlanActivity.class);
                startActivity(intent);
                break;
            case R.id.showPlan_lable:
                intent = new Intent(this,ShowPlanActivity.class);
                startActivity(intent);
                break;
            case R.id.option_button:
                intent = new Intent(this,OptionActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_button:
                finish();
                break;

        }
    }
}
