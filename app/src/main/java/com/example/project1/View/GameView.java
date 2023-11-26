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
    float playerPositionStartY;
    long touchStartTime;

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


    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                // Touch released, set velocity to zero
                AppConstants.getGameEngine().userPlayer.setPlayerVelocity(0);
                break;
            case MotionEvent.ACTION_DOWN:
                GameEngine.gameState = 1;
                // Save the initial touch position and time
                playerPositionStartY = event.getX();
                touchStartTime = System.currentTimeMillis();
                // Set a constant velocity for upward movement
                AppConstants.getGameEngine().userPlayer.setPlayerVelocity(-5);
                break;
            case MotionEvent.ACTION_MOVE:
                // No need to calculate deltaY in this case
                // Adjust sensitivity based on your desired responsiveness
                int sensitivity = 15;
                // Calculate the velocity using a constant value for upward movement
                int velocity = -sensitivity;
                // Apply the velocity to the player's input velocity
                AppConstants.getGameEngine().userPlayer.setPlayerVelocity(velocity);
                break;
        }
        return true;
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }

    //    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_UP:
//                playerPositionStartY = event.getY();
//                break;
//            case MotionEvent.ACTION_DOWN:
//                GameEngine.gameState = 1;
//                float playerTouchEndY = event.getY();
//                float playerSwipeY = playerTouchEndY - playerPositionStartY;
//                // Sensitivity
//                int velocity = (int) (-playerSwipeY * 0.1f);
//                AppConstants.getGameEngine().userPlayer.setPlayerVelocity(velocity);
//                performClick();
//                break;
//        }
//        return true;
//    }

}

