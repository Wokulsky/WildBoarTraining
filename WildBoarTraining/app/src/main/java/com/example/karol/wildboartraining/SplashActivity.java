package com.example.karol.wildboartraining;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View ImageView = findViewById(R.id.imageView);
        ImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageView:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }},500);
                        break;

        }
    }
}
