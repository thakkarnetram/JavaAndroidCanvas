package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project1.View.GameView;

public class GameActivity extends AppCompatActivity {
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}