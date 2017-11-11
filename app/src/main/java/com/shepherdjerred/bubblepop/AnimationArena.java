package com.shepherdjerred.bubblepop;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnimationArena {
    private final List<Ball> mBallList;
    private int mWidth;
    private int mHeight;
    private int mScore;
    private Paint mScorePaint;

    public AnimationArena(int arenaWidth, int arenaHeight) {
        mWidth = arenaWidth;
        mHeight = arenaHeight;
        mBallList = new ArrayList<>();

        mScorePaint = new Paint();
        mScorePaint.setColor(Color.BLACK);
        mScorePaint.setTextSize(50);

        init();
    }

    private void init() {
        for (int c = 0; c < 5; c++) {
            mBallList.add(new Ball(mWidth, mHeight));
        }
        mScore = 0;
    }

    public void update() {
        synchronized (mBallList) {
            for (Ball ball : mBallList) {
                ball.move();
            }
        }
    }

    public void draw(Canvas canvas) {
        // Wipe canvas clean
        canvas.drawRGB(255, 255, 255);
        canvas.drawText("Score: " + mScore, 10, 50, mScorePaint);

        synchronized (mBallList) {
            for (Ball ball : mBallList) {
                ball.draw(canvas);
            }
        }
    }

    // https://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
    // Safely delete from mBallList to avoid exception
    public void tap(int x, int y) {

        if (mBallList.size() == 0) {
            init();
            return;
        }

        boolean pop = false;

        synchronized (mBallList) {
            for (Iterator<Ball> it = mBallList.iterator(); !pop && it.hasNext(); ) {
                // Did user tap on ball?
                Ball ball = it.next();
                if (ball.ballTouch(x, y)) {
                    // Remove ball from mBallList
                    it.remove();
                    pop = true;
                }
            }
            if (!pop) {
                mBallList.add(new Ball(mWidth, mHeight));
            }
        }

        if (!pop) {
            mScore -= 2;
        } else {
            mScore++;
        }
    }
}
