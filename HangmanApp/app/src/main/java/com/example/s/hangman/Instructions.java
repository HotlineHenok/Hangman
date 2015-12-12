package com.example.s.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by S on 7-12-2015.
 */
public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
    }

    public void onPlay(View view) {
        Intent playScreen = new Intent(this, MainGameActivity.class);
        startActivity(playScreen);
    }
}
