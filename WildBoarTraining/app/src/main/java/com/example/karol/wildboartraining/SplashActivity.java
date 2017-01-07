package com.example.karol.wildboartraining;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        View boarImage = findViewById(R.id.boar_logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                   // boarImage.startAnimation(shake);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }},3000);
    }
}
