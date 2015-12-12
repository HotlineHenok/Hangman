package com.example.s.hangman;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
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
    private int numParts=6;
    private int currPart;
    private int numChars;
    private int numCorr;
    private StringBuilder showChar, wrongChar;
    private EditText WordText, GuessText;
    private ImageView[] bodyParts;

    public void playGame() {
        /* Load words from xml file */
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        currWord = "";

        String newWord = words[rand.nextInt(words.length)];

        while (newWord.equals(currWord))
            newWord = words[rand.nextInt(words.length)];

        currWord = newWord;
        currPart = 0;
        numChars = currWord.length();
        numCorr = 0;

        hideWord();
        for (int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }
    }

    private void hideWord() {
        /* fill textView with * char instead of word */
        showChar = new StringBuilder();
        wrongChar = new StringBuilder();
        for (int c = 0; c < currWord.length(); c++) {
            showChar.append("*");
        }
    }

    private void checkLetter(String letter) {

        /* takes letter from textfield */
        String chosenLetter = GuessText.getText().toString();
        char guessLetter = chosenLetter.charAt(0);

        /* iterates over characters of current word, true if letter is present */
        boolean correct = false;
        for (int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k) == guessLetter) {
                correct = true;
                numCorr++;
            }
        }

        if (correct) {
            /* if number of correct letters equals number of letters in current word, user wins */
            if (numCorr == numChars) {
                Intent winner = new Intent(this, Winner.class);
                winner.putExtra("score", currPart);
                startActivity(winner);
            }
        }
        else if (currPart < numParts) {
            /* if guessed character not in word, and total number of bodyparts not yet shown, show bodypart */
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        }
        else {
            /* if all bodyparts are shown (all turns used), playes loses, show alertdialog */
            AlertDialog.Builder lose = new AlertDialog.Builder(this);
            lose.setTitle("You Lose!");
            lose.setMessage("The correct answer was:\n\n" + currWord);

            /* player gets two options, to play again or toquit the game */
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
