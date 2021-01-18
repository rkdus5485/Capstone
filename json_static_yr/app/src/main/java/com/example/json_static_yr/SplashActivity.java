package com.example.json_static_yr;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startLoading();
    }
    private void startLoading(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}