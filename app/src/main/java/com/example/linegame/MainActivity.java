package com.example.linegame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Display;
import android.graphics.Point;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        pongGame = new PongGame(this, size.x, size.y);


        //Intent intent = new Intent(this, PongGame.class);
        //System.out.println("ye boi");
        //startActivity(intent);
    }

}