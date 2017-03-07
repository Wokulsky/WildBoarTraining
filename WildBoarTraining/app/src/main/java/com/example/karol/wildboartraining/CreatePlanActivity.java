package com.example.karol.wildboartraining;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.karol.wildboartraining.ConnectionPackage.ClientConnection;
import com.example.karol.wildboartraining.SurveyPackage.SurveyMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 05.02.2017.
 */

public class CreatePlanActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        View createPlanButton = findViewById(R.id.survey_button);
        createPlanButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.survey_button:
                startActivity(new Intent(this,SurveyMainActivity.class));
        }
    }
}
