package com.example.karol.wildboartraining;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.karol.wildboartraining.SurveyPackage.ZeroPageActivity;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;
import com.example.karol.wildboartraining.UtilitiesPackage.MySurveyPageActivity;

import java.util.HashMap;

public class SignUpSurveyActivity extends MySurveyPageActivity {

    private HashMap<String,String> parameters = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_survey);
        super.gestureObject = new GestureDetectorCompat(this,new LearnGesture(this,null,new Intent(this, ZeroPageActivity.class)));
        parameters = new HashMap<>();

    }
    public boolean onTouchEvent(MotionEvent event)
    {
        final EditText loginEditText = (EditText)this.findViewById(R.id.login_up_sur);
        final EditText passEditText = (EditText)this.findViewById(R.id.password_up_sur);
        final EditText fNameEditText = (EditText)this.findViewById(R.id.first_name_sur);
        final EditText lNameEditText = (EditText)this.findViewById(R.id.last_name_sur);
        addParameter(loginEditText,"login");
        addParameter(passEditText,"password");
        addParameter(fNameEditText,"name");
        addParameter(lNameEditText,"lastname");

        if (isEditTextFilled(loginEditText) && isEditTextFilled(passEditText) && isEditTextFilled(fNameEditText) && isEditTextFilled(lNameEditText) ) {

            return super.onTouchEvent(event);
        }
        return false;
    }
    public void addParameter( EditText editText, String type){

        String text = editText.getText().toString();
        if ( text.trim().equals("")){
            editText.setError("Wpisz dane");
        }else{
            if ( !text.matches("^[a-zA-Z0-9]*$")){
                editText.setError("Wpisz tylko cyfry i liczby");
                text = "";
            }
        }
        if ( !text.equals("")){
            this.parameters.put(type,text);
        }
    }
    private boolean isEditTextFilled(EditText editText){
        if (editText.getText().toString().trim().equals("")){
            editText.setError("Wpisz dane");
            return false;
        }
        return true;
    }
}
