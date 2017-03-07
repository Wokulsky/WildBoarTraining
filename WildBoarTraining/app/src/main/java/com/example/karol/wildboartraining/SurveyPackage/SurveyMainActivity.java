package com.example.karol.wildboartraining.SurveyPackage;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.karol.wildboartraining.R;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;

import android.view.MotionEvent;
public class SurveyMainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    private float x1,x2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        gestureObject = new GestureDetectorCompat(this,new LearnGesture(this,null,new Intent(this,FirstPageActivity.class)));

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if( Math.abs(deltaX) > 150 )
                    SurveyMainActivity.this.overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
                else
                    SurveyMainActivity.this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
        return super.onTouchEvent(event);
    }
}
