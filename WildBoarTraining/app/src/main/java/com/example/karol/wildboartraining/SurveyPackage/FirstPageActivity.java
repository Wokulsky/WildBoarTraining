package com.example.karol.wildboartraining.SurveyPackage;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.karol.wildboartraining.R;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;

public class FirstPageActivity extends AppCompatActivity {

    private float x1,x2;

    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        gestureObject = new GestureDetectorCompat(
                this,new LearnGesture(this,
                    new Intent(this,SurveyMainActivity.class),new Intent(this,SecoundPageActivity.class)));

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
                    FirstPageActivity.this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                else
                    FirstPageActivity.this.overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
                break;
        }
        return super.onTouchEvent(event);
    }
}
