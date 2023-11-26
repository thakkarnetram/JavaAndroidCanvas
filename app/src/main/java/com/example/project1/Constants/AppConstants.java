package com.example.project1.Constants;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.example.project1.BitMap.BitMapBank;
import com.example.project1.Engine.GameEngine;

public class AppConstants {
    static BitMapBank bitMapBank;
    public static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static public int gravity;
    static public int VELOCITY_JUMP;
    static GameEngine gameEngine;

    public static void initialization(Context context) {
        setScreenSize(context);
        bitMapBank = new BitMapBank(context.getResources());
        gameEngine = new GameEngine(context);
        AppConstants.gravity = 1;
        // -30
        AppConstants.VELOCITY_JUMP = 20;
    }

    public static BitMapBank getBitMapBank() {
        return bitMapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    public static void setScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int rotation = display.getRotation();
        boolean isLandscape = (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270);

        if (isLandscape) {
            // Swap width and height for landscape mode
            AppConstants.SCREEN_WIDTH = displayMetrics.heightPixels;
            AppConstants.SCREEN_HEIGHT = displayMetrics.widthPixels;
        } else {
            AppConstants.SCREEN_WIDTH = displayMetrics.widthPixels;
            AppConstants.SCREEN_HEIGHT = displayMetrics.heightPixels;
        }
    }
}
