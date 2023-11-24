package com.example.project1.Player;

public class PlayerBackGround {
    private int backGroundImageX, backGroundImageY, getBackGroundVelocity;

    public PlayerBackGround() {
        backGroundImageX = 0;
        backGroundImageY = 0;
        getBackGroundVelocity = 2;
    }

    public int getBackGroundImageX() {
        return backGroundImageX;
    }

    public int getBackGroundImageY() {
        return backGroundImageY;
    }

    public void setBackGroundImageX(int backGroundImageX) {
        this.backGroundImageX = backGroundImageX;
    }

    public void setBackGroundImageY(int backGroundImageY) {
        this.backGroundImageY = backGroundImageY;
    }
    public int getGetBackGroundVelocity() {
        return getBackGroundVelocity;
    }
}
