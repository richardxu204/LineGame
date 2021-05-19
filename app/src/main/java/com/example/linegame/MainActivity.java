package com.example.linegame;

import androidx.appcompat.app.AppCompatActivity;
import android.view.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v){
        System.out.println("ye boi");
    }

}