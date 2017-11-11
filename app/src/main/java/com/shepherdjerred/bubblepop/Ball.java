package com.shepherdjerred.bubblepop;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Ball {

    private int mRadius;
    private double mRadiusSquared;
    private int mBallColor;
    private Paint mPaint;
    private int mX;
    private int mY;
    private int mVelocityX;
    private int mVelocityY;
    private int mRightWall;
    private int mBottomWall;

    public Ball(int rightWall, int bottomWall) {
        Random random = new Random();
        mRadius = random.nextInt(200 + 1 - 100) + 100;
        mRadiusSquared =  Math.pow(mRadius, 2);
        mRightWall = rightWall;
        mBottomWall = bottomWall;
        mX = random.nextInt(100 + 1);
        mY = random.nextInt(100 + 1);
        mVelocityX = random.nextInt(20 + 1 - 5) + 5;
        mVelocityY = random.nextInt(20 + 1 - 5) + 5;
        mBallColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        mPaint = new Paint();
        mPaint.setColor(mBallColor);
    }

    public void move() {

        // Move ball
        mX += mVelocityX;
        mY += mVelocityY;

        // Check for top and bottom wall collisions
        if (mY > mBottomWall - mRadius) {
            mY = mBottomWall - mRadius;
            mVelocityY *= -1;
        } else if (mY < mRadius) {
            mY = mRadius;
            mVelocityY *= -1;
        }

        // Check for right and left wall collisions
        if (mX > mRightWall - mRadius) {
            mX = mRightWall - mRadius;
            mVelocityX *= -1;
        } else if (mX < mRadius) {
            mX = mRadius;
            mVelocityX *= -1;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(mX, mY, mRadius, mPaint);
    }

    public boolean ballTouch(int x, int y) {
        double distance = Math.pow(x - mX, 2) + Math.pow(y - mY, 2);
        return distance <= mRadiusSquared;
    }
}
