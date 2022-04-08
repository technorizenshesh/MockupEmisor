package com.sefueemisor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sefueemisor.databinding.ActivitySplashBinding;


public class SplashAct extends AppCompatActivity {
    ActivitySplashBinding binding;
    public static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);
    processNextActivity();
    }



    private void processNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashAct.this, WelcomeAct.class));
                    finish();
                }


        }, SPLASH_TIME_OUT);
    }


}