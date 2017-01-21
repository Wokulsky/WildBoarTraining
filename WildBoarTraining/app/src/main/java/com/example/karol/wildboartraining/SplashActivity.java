package com.example.karol.wildboartraining;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView image = (ImageView) findViewById(R.id.text_image);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.around);

        image.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*try {
                            sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }},2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
