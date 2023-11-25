package com.example.project1.View;

import android.content.Context;
import android.util.Log;
import android.view.ActionProvider;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.project1.Constants.AppConstants;
import com.example.project1.Engine.GameEngine;
import com.example.project1.Thread.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;
    private float playerPositionStartY;
    private long touchStartTime;

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (!gameThread.isRunning()) {
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (gameThread.isRunning()) {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    void initGameView() {
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(surfaceHolder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Save the initial touch position
                playerPositionStartY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float deltaY = event.getY() - playerPositionStartY;
                int sensitivity = 111;
                int velocity = (int) (deltaY * sensitivity);
                Log.e("TAG", "onTouchEvent: Velocity " + velocity );
                AppConstants.getGameEngine().userPlayer.setPlayerVelocity(velocity);
                playerPositionStartY = event.getY();
                performClick();
                break;
//            case MotionEvent.ACTION_UP:
//                AppConstants.getGameEngine().userPlayer.setPlayerVelocity(0);
//                break;
        }
        return true;
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
