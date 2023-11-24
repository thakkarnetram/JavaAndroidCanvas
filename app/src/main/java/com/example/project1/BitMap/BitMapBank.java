package com.example.project1.BitMap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.project1.Constants.AppConstants;
import com.example.project1.R;

public class BitMapBank {
    Bitmap backgroundGame, gameObstacle;
    Bitmap[] player;

    public BitMapBank(Resources resources) {
        // adding images and stuff
        backgroundGame = BitmapFactory.decodeResource(resources, R.drawable.bg);
        backgroundGame = scaleBackGroundImage(backgroundGame);
        // obstacles
        gameObstacle = BitmapFactory.decodeResource(resources, R.drawable.bottomtube);
        // player images
        player = new Bitmap[2];
        player[0] = BitmapFactory.decodeResource(resources, R.drawable.bird);
        player[1] = BitmapFactory.decodeResource(resources, R.drawable.bird2);
    }

    //  Getting the player from BitMap by Frames
    public Bitmap getPlayerByFrame(int frame) {
        int index = frame % player.length;
        return player[index];
    }

    // Getting player width & height
    public int getPlayerWidth() {
        return player[0].getWidth();
    }

    public int getPlayerHeight() {
        return player[0].getHeight();
    }

    // Getting BackGround Image
    public Bitmap getBackgroundGame() {
        return backgroundGame;
    }

    // Getting BackGround Width & Height
    public float getBackGroundWidth() {
        return backgroundGame.getWidth();
    }

    public float getBackGroundHeight() {
        return backgroundGame.getHeight();
    }

    // Getting Obstacle
    public Bitmap getGameObstacle() {
        return gameObstacle;
    }

    // Getting Obstacle Width & Height
    public int getGameObstacleWidth() {
        return gameObstacle.getWidth();
    }

    public int getGameObstacleHeight() {
        return gameObstacle.getHeight();
    }

    // Scaling the Image
    public Bitmap scaleBackGroundImage(Bitmap bitmap) {
        float widthHeightRatio = getBackGroundHeight() > 0 ? getBackGroundWidth() / getBackGroundHeight() : 1.0f;
        int backGroundScaleWidth = (int) (widthHeightRatio * AppConstants.SCREEN_HEIGHT);
        return Bitmap.createScaledBitmap(bitmap,backGroundScaleWidth,AppConstants.SCREEN_HEIGHT,false);
    }
}
