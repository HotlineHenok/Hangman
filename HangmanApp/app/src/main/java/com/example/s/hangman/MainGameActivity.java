package com.example.s.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageView;


public class MainGameActivity  extends AppCompatActivity {
    private EditText WordText, GuessText;
    private Button SettingsButton, InstButton, HighButton, RestartButton;
    private ImageView[] bodyParts;
    private int numParts=6;
    private Gameplay goodgameplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Gameplay goodgameplay = new Gameplay();

        WordText = (EditText) findViewById(R.id.word);
        GuessText = (EditText) findViewById(R.id.guess);
        SettingsButton = (Button) findViewById(R.id.settbutton);
        InstButton = (Button) findViewById(R.id.instructbutton);
        HighButton = (Button) findViewById(R.id.highbutton);
        RestartButton = (Button) findViewById(R.id.restartbutton);

        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView)findViewById(R.id.head);
        bodyParts[1] = (ImageView)findViewById(R.id.body);
        bodyParts[2] = (ImageView)findViewById(R.id.arm1);
        bodyParts[3] = (ImageView)findViewById(R.id.arm2);
        bodyParts[4] = (ImageView)findViewById(R.id.leg1);
        bodyParts[5] = (ImageView)findViewById(R.id.leg2);

        Gameplay.playGame();
    }

    /* method leads to setting */
    private void onSettings (View view) {
        Intent to_settings = new Intent(this, Settings.class);
        startActivity(to_settings);

    }

    /* method leads to Instructions */
    private void onInst (View view) {
        Intent to_inst = new Intent(this, Instructions.class);
        startActivity(to_inst);
    }

    /* method leads to Highscore screen */
    private void onHigh (View view) {
        Intent to_high = new Intent(this, Highscores.class);
        startActivity(to_high);
    }

    private void onRestart (View view) {

    }
}
