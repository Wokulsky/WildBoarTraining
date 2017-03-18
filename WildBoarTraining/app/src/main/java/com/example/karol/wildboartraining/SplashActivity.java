package com.example.karol.wildboartraining;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity  {

    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_IS_LOGGED= "islogged";
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView image = (ImageView) findViewById(R.id.text_image);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.around);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
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
                        //Wczytyujemy ustawienia - czy użytkownik jest zalogowany
                        boolean isLogged = true;
                        Intent intent;
                        try{
                            isLogged = preferences.getBoolean("islogged",false);
                        }catch (Exception e){

                        }
                        Log.d("TAG_ANIM_BOUNDLE",isLogged+"");
                        if (isLogged){
                            intent = new Intent(SplashActivity.this,MenuActivity.class);
                        }
                        else {
                            intent = new Intent(SplashActivity.this,SignInUpActivity.class);
                        }
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
