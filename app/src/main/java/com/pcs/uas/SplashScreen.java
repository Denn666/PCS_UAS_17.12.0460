package com.pcs.uas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(3000);
            startActivity(new Intent(this, MainActivity.class));
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
