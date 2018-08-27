package com.mistershorr.whichnumberisbigger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // instance variables for the widgets we need to
    // access programmatically
    private Button buttonLeft;
    private Button buttonRight;
    private TextView textViewScore;
    private int score;
    private int leftNum;
    private int rightNum;

    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        randomizeAndUpdateDisplay();
    }

    @SuppressLint("SetTextI18n")
    private void randomizeAndUpdateDisplay() {
        // access the string resources using getResources()
        String scoreString = getResources().getString(R.string.main_score);
        textViewScore.setText(scoreString + score);
        randomizeNumbers();
        // valueOf is used to prevent resource not found.
        // can't set an int to a text field directly
        buttonLeft.setText(String.valueOf(leftNum));
        buttonRight.setText(String.valueOf(rightNum));
    }

    private void randomizeNumbers() {
        // generate a random number for the left
        leftNum = genNumber();
        rightNum = genNumber();
        // generate a random number for the right but make sure it doesn't
        // match the left
        while(leftNum == rightNum) {
            rightNum = genNumber();
        }
    }

    private int genNumber() {
        int range = MAX_NUM - MIN_NUM + 1;
        return    MIN_NUM + (int)(Math.random() * range);
    }

    private void wireWidgets() {
        buttonLeft = findViewById(R.id.button_main_left);
        buttonRight = findViewById(R.id.button_main_right);
        textViewScore = findViewById(R.id.textview_main_score);
    }

    public void onLeftClick(View view) {
        checkAnswer(true);
    }

    public void onRightClick(View view) {
       checkAnswer(false);
    }

    public void checkAnswer(boolean leftPressed) {
        String message;
        if((leftNum > rightNum && leftPressed) ||
                rightNum > leftNum && !leftPressed) {
            score++;
            message = "Correct!";
        }
        else {
            score--;
            message = "Less Correct!";
        }
        randomizeAndUpdateDisplay();
        Toast.makeText(this, message,
                Toast.LENGTH_SHORT).show();
    }
}


















