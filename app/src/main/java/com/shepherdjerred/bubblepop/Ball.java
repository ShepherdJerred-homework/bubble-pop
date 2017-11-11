package com.shepherdjerred.bubblepop;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Ball {

    private int radius;
    private double radiusSquared;
    private int ballColor;
    private Paint mPaint;
    private int mX;
    private int mY;
    private int mVelocityX;
    private int mVelocityY;
    private int mRightWall;
    private int mBottomWall;

    public Ball(int rightWall, int bottomWall) {
        Random random = new Random();
        radius = random.nextInt(200 + 1 - 100) + 100;
        radiusSquared =  Math.pow(radius, 2);
        mRightWall = rightWall;
        mBottomWall = bottomWall;
        mX = random.nextInt(100 + 1);
        mY = random.nextInt(100 + 1);
        mVelocityX = random.nextInt(20 + 1 - 5) + 5;
        mVelocityY = random.nextInt(20 + 1 - 5) + 5;
        ballColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        mPaint = new Paint();
        mPaint.setColor(ballColor);
    }

    public void move() {

        // Move ball
        mX += mVelocityX;
        mY += mVelocityY;

        // Check for top and bottom wall collisions
        if (mY > mBottomWall - radius) {
            mY = mBottomWall - radius;
            mVelocityY *= -1;
        } else if (mY < radius) {
            mY = radius;
            mVelocityY *= -1;
        }

        // Check for right and left wall collisions
        if (mX > mRightWall - radius) {
            mX = mRightWall - radius;
            mVelocityX *= -1;
        } else if (mX < radius) {
            mX = radius;
            mVelocityX *= -1;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(mX, mY, radius, mPaint);
    }

    public boolean ballTouch(int x, int y) {
        double distance = Math.pow(x - mX, 2) + Math.pow(y - mY, 2);
        return distance <= radiusSquared;
    }
}
