package com.example.project1.Thread;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

import com.example.project1.Constants.AppConstants;

public class GameThread extends Thread {
    SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 30;

    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            startTime = SystemClock.uptimeMillis();
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas != null) {
                synchronized (surfaceHolder) {
                    AppConstants.getGameEngine().updateDrawableBackGroundImage(canvas);
                    AppConstants.getGameEngine().updateDrawPlayer(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            loopTime = SystemClock.uptimeMillis() - startTime;
            if (loopTime < DELAY) {
                try {
                    Thread.sleep(DELAY - loopTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean state) {
        isRunning = state;
    }
}
