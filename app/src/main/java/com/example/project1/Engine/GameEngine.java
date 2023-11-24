package com.example.project1.Engine;

import android.graphics.Canvas;

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
        int currentFrame = userPlayer.getPlayerCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitMapBank().getPlayerByFrame(currentFrame), userPlayer.getPlayerX(), userPlayer.getPlayerY(), null);
        currentFrame++;
        if (currentFrame > userPlayer.maxFrame) {
            currentFrame = 0;
        }
        userPlayer.setPlayerCurrentFrame(currentFrame);
    }

    public void handleUserPlayerMovement(){
        if(gameState == 1) {
            if(userPlayer.getPlayerY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) || userPlayer.getPlayerVelocity() < 0 ) {
                userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
                userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
            }
        }
    }
}

