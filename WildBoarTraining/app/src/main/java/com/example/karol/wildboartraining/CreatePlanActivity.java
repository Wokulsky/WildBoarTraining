package com.example.karol.wildboartraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class CreatePlanActivity extends AppCompatActivity implements View.OnClickListener {

    ClientSSLConnection client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        View connsectionButton = findViewById(R.id.connection_button);
        connsectionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v.getId() == R.id.connection_button) {
                client = new ClientSSLConnection();
                client.runConnection(v);
            }
        }
    }

