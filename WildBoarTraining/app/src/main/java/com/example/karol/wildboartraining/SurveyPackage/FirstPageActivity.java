package com.example.karol.wildboartraining.SurveyPackage;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.karol.wildboartraining.R;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;
import com.example.karol.wildboartraining.UtilitiesPackage.MySurveyPageActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

public class FirstPageActivity extends MySurveyPageActivity {

    private EditText ageEditText,heightEditText,weighteditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        super.gestureObject = new GestureDetectorCompat(this,
                new LearnGesture(this,new Intent(this,ZeroPageActivity.class),new Intent(this,SecoundPageActivity.class)));

        ageEditText = (EditText) findViewById(R.id.age_edit_text);
        heightEditText = (EditText) findViewById(R.id.height_edit_text);
        weighteditText = (EditText) findViewById(R.id.weight_text_edit);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (isEditTextFilled(ageEditText) && isEditTextFilled(heightEditText) && isEditTextFilled(weighteditText)) {
            return super.onTouchEvent(event);
        }
        return false;
    }
    private boolean isEditTextFilled(EditText editText){
        if (editText.getText().toString().trim().equals("")){
            editText.setError("Wypełnij pole");
            return false;
        }
        return true;
    }
}
