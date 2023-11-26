package com.example.project1.Player;

public class PlayerBackGroundObject {
    int backGroundObjectX, backGroundObjectY, backGroundObjectVelocity;

    public PlayerBackGroundObject() {
        backGroundObjectX = 0;
        backGroundObjectY = 0;
        backGroundObjectVelocity = 2;
    }

    public int getBackGroundObjectX() {
        return backGroundObjectX;
    }

    public void setBackGroundObjectX(int backGroundObjectX) {
        this.backGroundObjectX = backGroundObjectX;
    }

    public int getBackGroundObjectY() {
        return backGroundObjectY;
    }

    public void setBackGroundObjectY(int backGroundObjectY) {
        this.backGroundObjectY = backGroundObjectY;
    }

    public int getBackGroundObjectVelocity() {
        return backGroundObjectVelocity;
    }

    public void setBackGroundObjectVelocity(int backGroundObjectVelocity) {
        this.backGroundObjectVelocity = backGroundObjectVelocity;
    }

}
