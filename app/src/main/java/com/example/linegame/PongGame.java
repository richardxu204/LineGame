package com.example.linegame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.os.Bundle;

public class PongGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public PongGame(Context context, int x, int y){

    }

}