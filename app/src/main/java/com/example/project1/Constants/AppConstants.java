package com.example.project1.Constants;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.project1.BitMap.BitMapBank;
import com.example.project1.Engine.GameEngine;

public class AppConstants {
    static BitMapBank bitMapBank;
    public static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_JUMP;
    static GameEngine gameEngine;

    public static void initialization(Context context) {
        setScreenSize(context);
//        bitMapBank = new BitMapBank(context.getResources()); // TODO Constructor for BitMapBank
        gameEngine = new GameEngine();
        AppConstants.gravity = 2;
        // -30
        AppConstants.VELOCITY_JUMP = 30;
    }

    public static BitMapBank getBitMapBank() {
        return bitMapBank;
    }

    public static GameEngine gameEngine() {
        return gameEngine;
    }

    public static void setScreenSize(Context context) {
        // getting the context (info) from device
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // getting the default display size
        Display display = windowManager.getDefaultDisplay();
        // getting the metrics
        DisplayMetrics displayMetrics = new DisplayMetrics();
        // adding the metrics in the display
        display.getMetrics(displayMetrics);
        // adding values to variables
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}
