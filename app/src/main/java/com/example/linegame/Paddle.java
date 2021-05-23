package com.example.linegame;

import android.graphics.RectF;

public class Paddle {
    private RectF padRect;

    private float padLength;
    private float padHeight;
    private float padX;
    private float padY;
    private float padSpeed;
    private final int STOPPED = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    private int padStatus = STOPPED;
    private int screenX;
    private int screenY;

    public Paddle(int x, int y){
        screenX = x;
        screenY = y;
        padLength = screenX / 8;
        padHeight = screenY / 25;
    }
}
