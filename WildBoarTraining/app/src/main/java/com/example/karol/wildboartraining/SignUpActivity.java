package com.example.karol.wildboartraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;
import com.example.karol.wildboartraining.UtilitiesPackage.MyCheckBoxWatcher;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Karol on 05.02.2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private HashMap<String,String> parameters = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_signup);

        View signInButton = findViewById(R.id.signUpButton);
        signInButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if ( v.getId() == R.id.signInButton){

            String messageType = "RegisterNewClient";
            parameters = new HashMap<>();

            final EditText loginEditText = (EditText)this.findViewById(R.id.login_up);
            addParameter(loginEditText,"login");
            final EditText passEditText = (EditText)this.findViewById(R.id.password_up);
            addParameter(passEditText,"password");
            final EditText fNameEditText = (EditText)this.findViewById(R.id.first_name);
            addParameter(fNameEditText,"name");
            final EditText lNameEditText = (EditText)this.findViewById(R.id.last_name);
            addParameter(lNameEditText,"lastname");
            final EditText emailEditText = (EditText)this.findViewById(R.id.email);
            addParameter(emailEditText,"EMAIL");
            final EditText phoneEditText = (EditText)this.findViewById(R.id.phone_number);
            addParameter(phoneEditText,"PHONE");

            final CheckBox smsVerifyBox = (CheckBox) this.findViewById(R.id.VerifySmsBox);
            final CheckBox emailVerifyBox = (CheckBox) this.findViewById(R.id.VerifyEmailBox);

            String verify = "SMS";

            smsVerifyBox.addTextChangedListener(new MyCheckBoxWatcher(smsVerifyBox,emailVerifyBox));
            emailVerifyBox.addTextChangedListener(new MyCheckBoxWatcher(emailVerifyBox,smsVerifyBox));

            if (emailVerifyBox.isChecked()) verify = "EMAIL";
            parameters.put("verify_way",verify);

            if ( parameters.size() == 7 ){

                InputStream keyin = this.getResources().openRawResource(R.raw.testkeysore);
                ClientConnection client = new ClientConnection(keyin,"RegisterNewClient",parameters);
                String connectionResult = client.runConnection();

                if (connectionResult.equals("RegisterNewClient")) {

                    Toast.makeText(this, "Rejestracja Udana", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(this, "Błąd", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

        }

    }
    public void addParameter( EditText editText, String type){

        String text = editText.getText().toString();
        if ( text.trim().equals("")){
            editText.setError("Wpisz dane");
        }else{
            if ( text.matches("^[a-zA-Z0-9]*$")){
                editText.setError("Wpisz tylko cyfry i liczby");
                text = "";
            }
        }
        if ( !text.equals("")){
            this.parameters.put(type,text);
        }
    }

}
