package com.example.karol.wildboartraining;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;
import com.example.karol.wildboartraining.UtilitiesPackage.MyTextWatcher;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Karol on 05.02.2017.
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_signin);

        View signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            //Przyciskamy Zaloguj

            case R.id.signInButton:

                final HashMap<String,String> parameters = new HashMap<>();

                final EditText loginEditText = (EditText)this.findViewById(R.id.login_in);

                final EditText passEditText = (EditText)this.findViewById(R.id.password_in);

                //Nasłuchiwanie na działanie

                MyTextWatcher loginWatcher = new MyTextWatcher(loginEditText);

                loginEditText.addTextChangedListener(loginWatcher);

                MyTextWatcher passWatcher = new MyTextWatcher(passEditText);

                passEditText.addTextChangedListener(passWatcher);

                String password = passWatcher.getText();

                String login = loginWatcher.getText();

                InputStream keyin = this.getResources().openRawResource(R.raw.testkeysore);

                final ClientConnection client = new ClientConnection(keyin,"LoginRequest" ,parameters);

                if (!password.equals("") || !login.equals("")){

                    parameters.put("login",login);
                    parameters.put("password",password);


                    String connectionResult = client.runConnection();

                    if (connectionResult.equals("LoginRequest") && client.IsLogged()) {

                        Toast.makeText(this, "Zalogowano", Toast.LENGTH_SHORT).show();

                    }else{

                        if (connectionResult.equals("enter verify code") ){

                            final AlertDialog alertVerifyDialog = new AlertDialog.Builder(this).create();
                            String macAddress = "123";
                            LayoutInflater inflaterVerify = LayoutInflater.from(getApplicationContext());
                            alertVerifyDialog.setView(inflaterVerify.inflate(R.layout.verify_alert, null));
                            final HashMap<String,String> verifyParametrs = new HashMap<>();
                            verifyParametrs.put("login",parameters.get("login"));
                            verifyParametrs.put("password",parameters.get("password"));
                            verifyParametrs.put("id_device",macAddress);


                            alertVerifyDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Wyślij", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    EditText editTextVerify = (EditText)alertVerifyDialog.findViewById(R.id.verify);
                                    verifyParametrs.put("verify_code",editTextVerify.getText().toString());

                                    String ans = client.verifyClient(verifyParametrs);

                                    if ( ans.equals("AddDevice")){
                                        Toast.makeText(SignInActivity.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(SignInActivity.this, "Błędna weryfikacja", Toast.LENGTH_SHORT).show();
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
                    }
                }//if ( !password.equals("") || !login.equals("") )

                break;
        }//switch end
    }

}
