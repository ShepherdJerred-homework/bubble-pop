package com.shepherdjerred.bubblepop;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Ball {

    private final int RADIUS = 100;
    private final double RADIUS_SQUARED = Math.pow(RADIUS, 2);
    private final int BALL_COLOR = 0xffaaaaff;
    private Paint mPaint;
    private int mX;
    private int mY;
    private int mVelocityX;
    private int mVelocityY;
    private int mRightWall;
    private int mBottomWall;

    public Ball(int rightWall, int bottomWall) {
        mRightWall = rightWall;
        mBottomWall = bottomWall;
        mX = new Random().nextInt(100 + 1);
        mY = new Random().nextInt(100 + 1);
        mVelocityX = new Random().nextInt(15 + 1 - 5) + 5;
        mVelocityY = new Random().nextInt(15 + 1 - 5) + 5;
        mPaint = new Paint();
        mPaint.setColor(BALL_COLOR);
    }

    public void move() {

        // Move ball
        mX += mVelocityX;
        mY += mVelocityY;

        // Check for top and bottom wall collisions
        if (mY > mBottomWall - RADIUS) {
            mY = mBottomWall - RADIUS;
            mVelocityY *= -1;
        } else if (mY < RADIUS) {
            mY = RADIUS;
            mVelocityY *= -1;
        }

        // Check for right and left wall collisions
        if (mX > mRightWall - RADIUS) {
            mX = mRightWall - RADIUS;
            mVelocityX *= -1;
        } else if (mX < RADIUS) {
            mX = RADIUS;
            mVelocityX *= -1;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(mX, mY, RADIUS, mPaint);
    }

    public boolean ballTouch(int x, int y) {
        double distance = Math.pow(x - mX, 2) + Math.pow(y - mY, 2);
        return distance <= RADIUS_SQUARED;
    }
}
