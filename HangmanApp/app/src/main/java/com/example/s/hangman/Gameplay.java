package com.example.s.hangman;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by S on 1-12-2015.
 */
public class Gameplay extends AppCompatActivity{
    private String[] words;
    private Random rand;
    private String currWord;
    private TextView[] charViews;
    private LinearLayoutCompat wordLayout;
    private ImageView[] bodyParts;
    private int numParts=6;
    private int currPart;
    private int numChars;
    private int numCorr;
    private EditText WordText, GuessText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        currWord = "";
        WordText = (EditText) findViewById(R.id.word);
        GuessText = (EditText) findViewById(R.id.guess);

        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView)findViewById(R.id.head);
        bodyParts[1] = (ImageView)findViewById(R.id.body);
        bodyParts[2] = (ImageView)findViewById(R.id.arm1);
        bodyParts[3] = (ImageView)findViewById(R.id.arm2);
        bodyParts[4] = (ImageView)findViewById(R.id.leg1);
        bodyParts[5] = (ImageView)findViewById(R.id.leg2);

        playGame();
    }

    private void playGame() {
        String newWord = words[rand.nextInt(words.length)];

        while (newWord.equals(currWord))
            newWord = words[rand.nextInt(words.length)];

        currWord = newWord;
        currPart = 0;
        numChars = currWord.length();
        numCorr = 0;




        for (int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }

        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();

        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText(""+currWord.charAt(c));

            charViews[c].setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            wordLayout.addView(charViews[c]);

        }
    }

    private void checkLetter(View v) {
        String chosenLetter = GuessText.getText().toString();
        char guessLetter = chosenLetter.charAt(0);

        boolean correct = false;
        for (int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k) == guessLetter) {
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }

        if (correct) {
            if (numCorr == numChars) {

            }
        }
        else if (currPart < numParts) {
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        }
        else {
            AlertDialog.Builder lose = new AlertDialog.Builder(this);
            lose.setTitle("You Lose!");
            lose.setMessage("The correct answer was:\n\n" + currWord);
            lose.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Gameplay.this.playGame();
                }
            });
            lose.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Gameplay.this.finish();
                }
            });
            lose.show();
        }

    }
}
