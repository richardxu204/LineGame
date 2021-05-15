package com.example.linegame;

public class Ball{
    private RectF ballRect;
    private float xVel;
    private float yVel;
    private float ballWidth;
    private float ballHeight;

    public Ball(int screenX, int screenY){
        /* initialize size and speed of the ball */
        ballWidth = screenX / 100;
        ballHeight = ballWidth;

        xVel = screenY / 4;
        yVel = xVel;

    }

    public RectF getRect(){
        return ballRect;
    }

    public void update(long fps){
        ballRect.left = ballRect.left + (xVel / fps);
        ballRect.top = ballRect.top + (yVel / fps);
        ballRect.right = ballRect.left + ballWidth;
        ballRect.bottom = ballRect.top - ballHeight;
    }

    // Reverse the vertical heading
    public void reverseYVelocity(){
        yVel = -yVel;
    }

    // Reverse the horizontal heading
    public void reverseXVelocity(){
        xVel = -xVel;
    }

    public void setRandomXVelocity(){

        // Generate a random number either 0 or 1
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }

    // Speed up by 10%
    // A score of over 20 is quite difficult
    // Reduce or increase 10 to make this easier or harder
    public void increaseVelocity(){
        xVel = xVel + (xVel / 10);
        yVel = yVel + (yVel / 10);
    }

    public void clearObstacleY(float y){
        ballRect.bottom = y;
        ballRect.top = y - ballHeight;
    }

    public void clearObstacleX(float x){
        ballRect.left = x;
        ballRect.right = x + ballWidth;
    }

    public void reset(int x, int y){
        ballRect.left = x / 2;
        ballRect.top = y - 20;
        ballRect.right = x / 2 + ballWidth;
        ballRect.bottom = y - 20 - ballHeight;
    }
}
