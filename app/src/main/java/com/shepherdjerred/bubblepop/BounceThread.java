package com.shepherdjerred.bubblepop;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class BounceThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private AnimationArena mAnimationArena;
    private boolean mIsRunning;

    public BounceThread(SurfaceHolder sh) {
        mIsRunning = true;
        mSurfaceHolder = sh;
        Canvas canvas = mSurfaceHolder.lockCanvas();
        mAnimationArena = new AnimationArena(canvas.getWidth(), canvas.getHeight());
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void run() {
        try {
            while (mIsRunning) {
                Canvas canvas = mSurfaceHolder.lockCanvas();
                mAnimationArena.update();
                mAnimationArena.draw(canvas);
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void onTouchEvent(int x, int y) {
        mAnimationArena.tap(x, y);
    }

    public void endBounce() {
        mIsRunning = false;
    }
}
