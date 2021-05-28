package com.example.linegame;

import android.graphics.RectF;

public class Paddle {
    private RectF padRect;

    private float padLength;
    private float padHeight;
    private float padX;
    private float padY;
    private float padSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;
    private int padStatus = STOPPED;
    private int screenX;
    private int screenY;

    public Paddle(int x, int y){
        screenX = x;
        screenY = y;
        padLength = screenX / 8;
        padHeight = screenY / 25;
        padX = screenX / 2;
        padY = screenY - 20;

        padRect = new RectF(padX, padY,  padX + padLength, padY + padHeight);
        padSpeed = screenX;

    }

    public RectF getRect(){
        return padRect;
    }

    public void setMovement(int state){
        padStatus = state;
    }

    public void update(long fps){

        if(padStatus == LEFT){
            padX = padX - padSpeed / fps;
        }

        if(padStatus == RIGHT){
            padX = padX + padSpeed / fps;
        }

        if(padRect.left < 0){
            padX = 0;
        }

        if(padRect.right > screenX){
            padX = screenX - (padRect.right - padRect.left);
        }

        padRect.left = padX;
        padRect.right = padX + padLength;
    }

}
