package com.example.karol.wildboartraining.SurveyPackage;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.karol.wildboartraining.R;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;
import com.example.karol.wildboartraining.UtilitiesPackage.MySurveyPageActivity;

public class SecoundPageActivity extends MySurveyPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        super.gestureObject = new GestureDetectorCompat(
                this,new LearnGesture(this,
                    new Intent(this,FirstPageActivity.class),new Intent(this,ThirdPageActivity.class)));

    }
}