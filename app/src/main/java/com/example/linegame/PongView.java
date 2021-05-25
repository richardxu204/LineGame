package com.example.linegame;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class PongView extends SurfaceView implements Runnable{
    //private final int explosionID;
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

    public PongView(Context context, int x, int y){
        super(context);
        screenX = x;
        screenY = y;

        pongHolder = getHolder();
        pongPaint = new Paint();

        pongPad = new Paddle(screenX, screenY);
        pongBall = new Ball(screenX, screenY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            pongSounds = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            pongSounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }


        try{
            // Create objects of the 2 required classes
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("beep1.ogg");
            beep1ID = pongSounds.load(descriptor, 0);

            descriptor = assetManager.openFd("beep2.ogg");
            beep2ID = pongSounds.load(descriptor, 0);

            descriptor = assetManager.openFd("beep3.ogg");
            beep3ID = pongSounds.load(descriptor, 0);

            descriptor = assetManager.openFd("loseLife.ogg");
            loseLifeID = pongSounds.load(descriptor, 0);

            //descriptor = assetManager.openFd("explode.ogg");
            //explosionID = pongSounds.load(descriptor, 0);

        }catch(IOException e){
            // Print an error message to the console
            Log.e("error", "failed to load sound files");
        }

        setupAndRestart();
    }

    public void setupAndRestart(){

        // Put the mBall back to the start
        pongBall.reset(screenX, screenY);

        // if game over reset scores and mLives
        if(pongLives == 0) {
            pongScore = 0;
            pongLives = 3;
        }

    }

    @Override
    public void run() {
        while (playStatus) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            // Update the frame
            if(!playPaused){
                update();
            }

            // Draw the frame
            draw();

        /*
            Calculate the FPS this frame
            We can then use the result to
            time animations in the update methods.
        */
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                pongFPS = 1000 / timeThisFrame;
            }

        }

    }

    public void update(){
        pongPad.update(pongFPS);
        pongBall.update(pongFPS);

        if(RectF.intersects(pongPad.getRect(), pongBall.getRect())){
            pongBall.setRandomVel();
            pongBall.reverseYVelocity();
            pongBall.bounceY();
            pongScore++;
            pongBall.increaseVel();

            pongSounds.play(beep1ID, 1, 1, 0, 0, 1);
        }
        if(pongBall.getRect().bottom > screenY){
            pongBall.reverseYVelocity();
            pongBall.bounceY(screenY - 2);

            pongLives--;
            pongSounds.play(loseLifeID, 1, 1, 0, 0, 1);
            if(pongLives == 0){
                playPaused = true;
                setupAndRestart();
            }
        }

    }

}
