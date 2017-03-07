package com.example.karol.wildboartraining.SurveyPackage;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;

import com.example.karol.wildboartraining.R;
import com.example.karol.wildboartraining.UtilitiesPackage.LearnGesture;
import com.example.karol.wildboartraining.UtilitiesPackage.OnlyOneCheckBox;

public class SecoundPageActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox box1,box2,box3,box4;
    private float x1,x2;
    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        box1 = (CheckBox)findViewById(R.id.survSec_first_checkBox);
        box2 = (CheckBox)findViewById(R.id.survSec_second_checkBox);
        box3 = (CheckBox)findViewById(R.id.survSec_third_checkBox);
        box4 = (CheckBox)findViewById(R.id.survSec_four_checkBox);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound_page);
        gestureObject = new GestureDetectorCompat(
                this,new LearnGesture(this,
                new Intent(this,FirstPageActivity.class),new Intent(this,EndPageActivity.class)));

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
                    SecoundPageActivity.this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                else
                    SecoundPageActivity.this.overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.survSec_first_checkBox:
                box1.setOnCheckedChangeListener(new OnlyOneCheckBox(box2,box3,box4));
                break;
            case R.id.survSec_second_checkBox:
                box2.setOnCheckedChangeListener(new OnlyOneCheckBox(box1,box3,box4));
                break;
            case R.id.survSec_third_checkBox:
                box3.setOnCheckedChangeListener(new OnlyOneCheckBox(box1,box2,box4));
                break;
            case R.id.survSec_four_checkBox:
                box4.setOnCheckedChangeListener(new OnlyOneCheckBox(box1,box2,box3));
                break;

        }
    }
}
