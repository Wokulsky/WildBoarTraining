package com.example.karol.wildboartraining.SurveyPackage;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.karol.wildboartraining.R;
import com.example.karol.wildboartraining.SignUpSurveyActivity;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;
import com.example.karol.wildboartraining.UtilitiesPackage.MySurveyPageActivity;

public class ZeroPageActivity extends MySurveyPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero_page);
        gestureObject = new GestureDetectorCompat(
                this,new LearnGesture(this,
                new Intent(this,SignUpSurveyActivity.class),new Intent(this,FirstPageActivity.class)));
    }
}
