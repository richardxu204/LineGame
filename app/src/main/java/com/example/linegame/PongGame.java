package com.example.linegame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class PongGame extends AppCompatActivity {
    PongGame pongView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        pongView = new PongView(this, size.x, size.y);
        setContentView(pongView);
    }


    @Override
    protected void onResume(){
        super.onResume();
        pongView.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        pongView.pause();
    }

}