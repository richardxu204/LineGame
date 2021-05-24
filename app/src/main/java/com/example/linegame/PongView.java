package com.example.linegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.SoundPool;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongView extends SurfaceView implements Runnable{
    Thread gameThread = null;

    SurfaceHolder pongHolder;

    volatile boolean playStatus;

    boolean playPaused = true;

    Canvas pongCanvas;
    Paint pongPaint;

    long pongFPS;

    int screenX;
    int screenY;

    Paddle pongPad;
    Ball pongBall;

    SoundPool pongSounds;
    int beep1ID = -1;
    int beep2ID = -1;
    int beep3ID = -1;
    int loseLifeID = -1;

    // The mScore
    int pongScore = 0;

    // Lives
    int pongLives = 3;


}
