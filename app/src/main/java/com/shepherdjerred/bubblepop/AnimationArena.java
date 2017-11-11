package com.shepherdjerred.bubblepop;


import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnimationArena {
    private final List<Ball> mBallList;
    private int mWidth;
    private int mHeight;

    public AnimationArena(int arenaWidth, int arenaHeight) {
        mWidth = arenaWidth;
        mHeight = arenaHeight;
        mBallList = new ArrayList<>();
        for (int c = 0; c < 5; c++) {
            mBallList.add(new Ball(mWidth, mHeight));
        }
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

        synchronized (mBallList) {
            for (Ball ball : mBallList) {
                ball.draw(canvas);
            }
        }
    }

    // https://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
    // Safely delete from mBallList to avoid exception
    public void tap(int x, int y) {

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
        }
    }
}
