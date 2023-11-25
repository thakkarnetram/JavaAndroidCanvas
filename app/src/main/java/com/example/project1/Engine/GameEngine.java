package com.example.project1.Engine;

import android.graphics.Canvas;
import android.util.Log;

import com.example.project1.Constants.AppConstants;
import com.example.project1.Player.PlayerBackGround;
import com.example.project1.Player.UserPlayer;

public class GameEngine {
    PlayerBackGround playerBackGround;
    public UserPlayer userPlayer;
    static public int gameState;

    public GameEngine() {
        playerBackGround = new PlayerBackGround();
        userPlayer = new UserPlayer();
        gameState = 0;
    }

    public void updateDrawableBackGroundImage(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        playerBackGround.setBackGroundImageX(playerBackGround.getBackGroundImageX() - playerBackGround.getGetBackGroundVelocity());
        if (playerBackGround.getBackGroundImageX() < -AppConstants.getBitMapBank().getBackGroundWidth()) {
            playerBackGround.setBackGroundImageX(0);
        }
        canvas.drawBitmap(AppConstants.getBitMapBank().getBackgroundGame(), playerBackGround.getBackGroundImageX(), playerBackGround.getBackGroundImageY(), null);
        if (playerBackGround.getBackGroundImageX() < -(AppConstants.getBitMapBank().getBackGroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitMapBank().getBackgroundGame(), playerBackGround.getBackGroundImageX() + AppConstants.getBitMapBank().getBackGroundWidth(), playerBackGround.getBackGroundImageY(), null);
        }
    }

    public void updateDrawPlayer(Canvas canvas) {
        if (gameState == 1) {
            if (userPlayer.getPlayerY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) || userPlayer.getPlayerVelocity() < 0) {
                userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
                userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
            }
        }
        Log.e("GET Y ", "updateDrawPlayer: GET Y" + userPlayer.getPlayerY() );
        int currentFrame = userPlayer.getPlayerCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitMapBank().getPlayerByFrame(currentFrame), userPlayer.getPlayerX(), userPlayer.getPlayerY(), null);
        currentFrame++;
        if (currentFrame > userPlayer.maxFrame) {
            currentFrame = 0;
        }
        userPlayer.setPlayerCurrentFrame(currentFrame);
    }

//    public void handleUserPlayerMovement(){
//        if(gameState == 1) {
//            if(userPlayer.getPlayerY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) || userPlayer.getPlayerVelocity() < 0 ) {
//                userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
//                userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
//            }
//            int playerPositionY = userPlayer.getPlayerY();
//            int playerHeight = AppConstants.getBitMapBank().getPlayerHeight();
//            // collision checking with top screen
//            if (playerPositionY < 0 ) {
//                userPlayer.setPlayerY(0);
//            }
//            // collision checking with bottom screen
//            if (playerPositionY + playerHeight > AppConstants.SCREEN_HEIGHT) {
//                userPlayer.setPlayerY(AppConstants.SCREEN_HEIGHT - playerHeight);
//            }
//            // gravity & update position of player
//            userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
//            userPlayer.setPlayerY(playerPositionY + userPlayer.getPlayerVelocity());
//        }
//    }

    public void handleUserPlayerMovement() {
        if (gameState == 1) {
            // Update player position based on gravity
            userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
            userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
            // Check for collision with the top of the screen
            if (userPlayer.getPlayerY() < 0) {
                userPlayer.setPlayerY(0);
                userPlayer.setPlayerVelocity(0);
            }

            // Check for collision with the bottom of the screen
            if (userPlayer.getPlayerY() > AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) {
                userPlayer.setPlayerY(AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight());
                userPlayer.setPlayerVelocity(0);
            }
        }
    }

}

