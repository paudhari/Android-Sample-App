package com.hari.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.hari.R;


public class SplashActivity extends AppCompatActivity {


    private long delayTimer = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ItemListActivity.class);
                startActivity(intent);
                finish();
            }
        }, delayTimer);


    }


}
