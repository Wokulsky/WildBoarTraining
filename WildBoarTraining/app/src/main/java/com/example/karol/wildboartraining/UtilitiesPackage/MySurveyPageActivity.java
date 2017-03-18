package com.example.karol.wildboartraining.UtilitiesPackage;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.example.karol.wildboartraining.R;

/**
 * Created by Karol on 14.03.2017.
 */

public abstract class MySurveyPageActivity extends AppCompatActivity {

    protected GestureDetectorCompat gestureObject;
    protected float x1,x2;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.gestureObject.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                Log.d("TAG_deltaX", deltaX + "");
                if (deltaX < 0) {
                    this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    Log.d("TAG_SLIDE", "right to left");
                } else {
                    this.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                    Log.d("TAG_SLIDE", "left to right");
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
