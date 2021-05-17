package com.example.linegame;
import android.graphics.RectF;
import java.util.Random;


public class Bat {
    // RectF is an object that holds four coordinates - just what we need
    private RectF batRect;

    // How long and high our mBat will be
    private float batLength;
    private float batHeight;

    // X is the far left of the rectangle which forms our mBat
    private float xCoord;

    // Y is the top coordinate
    private float yCoord;

    // This will hold the pixels per second speed that
    // the mBat will move
    private float batSpeed;

    // Which ways can the mBat move
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    // Is the mBat moving and in which direction
    private int batMoving = STOPPED;

    // The screen length and width in pixels
    private int screenX;
    private int screenY;

    public Bat(int x, int y){

        screenX = x;
        screenY = y;

        // 1/8 screen width wide
        batLength = screenX / 8;

        // 1/25 screen mHeight high
        batHeight = screenY / 25;

        // Start mBat in roughly the sceen centre
        xCoord = screenX / 2;
        yCoord = screenY - 20;

        batRect = new RectF(xCoord, yCoord, xCoord + batLength, yCoord + batHeight);

        // How fast is the mBat in pixels per second
        batSpeed = screenX;
        // Cover entire screen in 1 second
    }

    public RectF getRect(){
        return batRect;
    }

    public void setMovementState(int state){
        batMoving = state;
    }

    public void update(long fps){
        if(batMoving == LEFT){
            xCoord = xCoord - batSpeed / fps;
        }

        if(batMoving == RIGHT){
            xCoord = xCoord + batSpeed / fps;
        }

        // Make sure it's not leaving screen
        if(batRect.left < 0){
            xCoord = 0;
        }
        if(batRect.right > screenX){
            xCoord = screenX - (batRect.right - batRect.left);
        }

        // Update the Bat graphics
        batRect.left = xCoord;
        batRect.right = xCoord + batLength;
    }
}
