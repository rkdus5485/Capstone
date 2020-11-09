package com.example.static_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        startLoading();
    }

//        Handler hd = new Handler();
//        hd.postDelayed(new splashhandler(), 3000);
//    }
//    private class splashhandler implements Runnable{
//        public void run(){
//            startActivity(new Intent(getApplication(), MainActivity.class));
//            Splash.this.finish();
//        }
//    }
//    @Override
//    public void onBackPressed(){
//
//    }
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
