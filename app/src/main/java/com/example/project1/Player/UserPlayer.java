package com.example.project1.Player;

import com.example.project1.Constants.AppConstants;

public class UserPlayer {
    private int playerX , playerY , playerCurrentFrame , playerVelocity;
    public static int maxFrame;
    public UserPlayer() {
        // TODO GetPlayerWidth & Height To Be Done In BitMapBank
        playerX = AppConstants.SCREEN_WIDTH/2 - AppConstants.getBitMapBank().getPlayerWidth()/2;
        playerY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitMapBank().getPlayerHeight()/2;
        playerCurrentFrame= 0;
        playerVelocity = 0;
        maxFrame = 2;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public int getPlayerCurrentFrame() {
        return playerCurrentFrame;
    }

    public void setPlayerCurrentFrame(int playerCurrentFrame) {
        this.playerCurrentFrame = playerCurrentFrame;
    }

    public int getPlayerVelocity() {
        return playerVelocity;
    }

    public void setPlayerVelocity(int playerVelocity) {
        this.playerVelocity = playerVelocity;
    }

    public static int getMaxFrame() {
        return maxFrame;
    }

}
