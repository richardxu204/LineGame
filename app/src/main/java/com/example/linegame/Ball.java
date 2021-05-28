package com.example.linegame;

import android.graphics.RectF;
import java.util.Random;

public class Ball {
    //Ball properties
    private RectF ballRect;
    private float ballXVel;
    private float ballYVel;
    private float ballInitVel;
    private float ballSize;

    //Ball constructor
    public Ball(int screenX, int screenY){
        ballSize = screenX / 100;
        ballXVel = screenY / 4;
        ballYVel = ballXVel;
        ballInitVel = ballXVel;
        ballRect = new RectF();
    }

    public RectF getRect(){

        return ballRect;
    }

    public void update(long fps){
        ballRect.left = ballRect.left + (ballXVel / fps);
        ballRect.top = ballRect.top + (ballYVel / fps);
        ballRect.right = ballRect.left + ballSize;
        ballRect.bottom = ballRect.top - ballSize;
    }

    // Reverse the vertical heading
    public void reverseYVelocity(){
        ballYVel = -ballYVel;
    }

    // Reverse the horizontal heading
    public void reverseXVelocity(){
        ballXVel = -ballXVel;
    }

    public void increaseVel(){
        ballXVel = ballXVel + ballXVel / 10;
        ballYVel = ballYVel + ballYVel / 10;
    }

    public void setRandomVel(){
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }

    public void bounceY(float y){
        ballRect.bottom = y;
        ballRect.top = y - ballSize;
    }

    public void bounceX(float x){
        ballRect.left = x;
        ballRect.right = x - ballSize;
    }

    public void reset(int x, int y){
        ballRect.left = x / 2;
        ballRect.top = y - 20;
        ballRect.right = x / 2 + ballSize;
        ballRect.bottom = y - 20 - ballSize;
        ballXVel = ballInitVel;
        ballYVel = ballXVel;
    }

}
