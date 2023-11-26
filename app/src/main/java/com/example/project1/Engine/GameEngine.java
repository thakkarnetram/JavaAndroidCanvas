package com.example.project1.Engine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;

import com.example.project1.Constants.AppConstants;
import com.example.project1.Player.PlayerBackGround;
import com.example.project1.Player.PlayerBackGroundObject;
import com.example.project1.Player.UserPlayer;
import com.example.project1.RestartGameActivity;

import java.util.List;


public class GameEngine {
    PlayerBackGround playerBackGround;
    PlayerBackGroundObject playerBackGroundObject;
    public UserPlayer userPlayer;
    static public int gameState;

    private List<Integer> objectPositions;

    Context context;

    public GameEngine(Context context) {
        this.context = context;
        playerBackGround = new PlayerBackGround();
        playerBackGroundObject = new PlayerBackGroundObject();
        userPlayer = new UserPlayer();
        gameState = 0;

    }

    public void updateDrawableBackGroundImage(Canvas canvas) {
        if (canvas == null) {
            return;
        }

        if (GameEngine.gameState == 0) {
            // Reset the background to its initial state when the game restarts
            playerBackGround.setBackGroundImageX(0);
        } else {
            // Move the background as the player progresses
            playerBackGround.setBackGroundImageX(playerBackGround.getBackGroundImageX() - playerBackGround.getGetBackGroundVelocity());

            // Check if the background has moved beyond its width
            if (playerBackGround.getBackGroundImageX() < -AppConstants.getBitMapBank().getBackGroundWidth()) {
                // Reset the background to create a looping effect
                playerBackGround.setBackGroundImageX(0);
            }
        }

        // Draw the background on the canvas
        canvas.drawBitmap(AppConstants.getBitMapBank().getBackgroundGame(),
                playerBackGround.getBackGroundImageX(), playerBackGround.getBackGroundImageY(), null);

        // Draw a second copy of the background for a smooth transition
        if (playerBackGround.getBackGroundImageX() < -(AppConstants.getBitMapBank().getBackGroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitMapBank().getBackgroundGame(),
                    playerBackGround.getBackGroundImageX() + AppConstants.getBitMapBank().getBackGroundWidth(),
                    playerBackGround.getBackGroundImageY(), null);
        }
    }

    // TODO implementation of object
//    public void updateDrawObject(Canvas canvas) {
//        if (canvas == null) {
//            return;
//        }
//        // Resetting objects when game restarts
//        if (GameEngine.gameState == 0) {
//            for (int i = 0; i < numberOfObjects; i++) {
//                // Randomly set X position for each object
//                int randomX = new Random().nextInt(AppConstants.SCREEN_WIDTH);
//                objectPositions[i] = randomX;
//            }
//        } else {
//            for (int i = 0; i < numberOfObjects; i++) {
//                // Move each object to the left
//                objectPositions[i] -= playerBackGroundObject.getBackGroundObjectVelocity();
//
//                // If the object is off the screen, reset its position
//                if (objectPositions[i] < -AppConstants.getBitMapBank().getGameObstacleWidth()) {
//                    objectPositions[i] = AppConstants.SCREEN_WIDTH;
//                }
//            }
//        }
//
//        // Drawing objects on the canvas
//        for (int i = 0; i < numberOfObjects; i++) {
//            canvas.drawBitmap(AppConstants.getBitMapBank().getGameObstacle(), objectPositions[i], playerBackGroundObject.getBackGroundObjectY(), null);
//        }
//    }

    public void updateDrawPlayer(Canvas canvas) {
        if (gameState == 1) {
            if (userPlayer.getPlayerY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) || userPlayer.getPlayerVelocity() < 0) {
                userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
                userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
            }
        }
        Log.e("GET Y ", "updateDrawPlayer: GET Y" + userPlayer.getPlayerY());
        int currentFrame = userPlayer.getPlayerCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitMapBank().getPlayerByFrame(currentFrame), userPlayer.getPlayerX(), userPlayer.getPlayerY(), null);
        currentFrame++;
        if (currentFrame > userPlayer.maxFrame) {
            currentFrame = 0;
        }
        userPlayer.setPlayerCurrentFrame(currentFrame);
    }


    public void handleUserPlayerMovement() {
        if (gameState == 1) {
            // Update player position based on gravity
            userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
            userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
            // Check for collision with the top of the screen
            if (userPlayer.getPlayerY() < 0) {
                userPlayer.setPlayerY(0);
                userPlayer.setPlayerVelocity(0);
                restartGame();
            }

            // Check for collision with the bottom of the screen
            if (userPlayer.getPlayerY() > AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) {
                userPlayer.setPlayerY(AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight());
                userPlayer.setPlayerVelocity(0);
                restartGame();
            }
        }
    }


    // restart game when something is hit
    private void restartGame() {
        // Reset player position to a safe starting position
        userPlayer.setPlayerY(AppConstants.SCREEN_HEIGHT / 2 - AppConstants.getBitMapBank().getPlayerHeight() / 2);
        userPlayer.setPlayerVelocity(0);

        // Check if the game is not already in the restart state
        if (gameState != 0) {
            Intent intent = new Intent(context, RestartGameActivity.class);
            context.startActivity(intent);
            // Setting the game state to restart to avoid entering an endless loop
            gameState = 0;
        }
    }


    //    public void handleUserPlayerMovement() {
//        if (gameState == 1) {
//            // Update player position based on gravity
//            userPlayer.setPlayerVelocity(userPlayer.getPlayerVelocity() + AppConstants.gravity);
//            userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
//
//            // Check for collision with the top of the screen
//            if (userPlayer.getPlayerY() < 0) {
//                userPlayer.setPlayerY(0);
//                userPlayer.setPlayerVelocity(0);
//            }
//
//            // Check for collision with the bottom of the screen
//            if (userPlayer.getPlayerY() > AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight()) {
//                userPlayer.setPlayerY(AppConstants.SCREEN_HEIGHT - AppConstants.getBitMapBank().getPlayerHeight());
//                userPlayer.setPlayerVelocity(0);
//            }
//
//            // Update player position based on user input
//            userPlayer.setPlayerY(userPlayer.getPlayerY() + userPlayer.getPlayerVelocity());
//
//            // Reset user input velocity after updating player position
//            userPlayer.setPlayerVelocity(0);
//        }
//    }


    // delay doesnt work properly so not using
//    private void restartGameWithDelay() {
//        // Delay the restart
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                restartGame();
//            }
//        }, 2000); // Adjust the delay time as needed
//    }


}

