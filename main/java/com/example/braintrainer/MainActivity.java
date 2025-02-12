package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // CAN CHANGE RANGE OF QUESTION AND THE ERROR
    final int MAX = 21;
    final int MIN = 0;
    final int MAX_ERROR = 5;
    final int MIN_ERROR = -5;

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    GridLayout gridLayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        gridLayout.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(MAX - MIN) + MIN;
        int b = rand.nextInt(MAX - MIN) + MIN;

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int ans = a + b;
        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(ans);
            } else {
                int wrongAnswer = ans + rand.nextInt(MAX_ERROR - MIN_ERROR) + MIN_ERROR;
                if (i == 1) {
                    while (wrongAnswer == ans || wrongAnswer == answers.get(0)) {
                        wrongAnswer = ans + rand.nextInt(MAX_ERROR - MIN_ERROR) + MIN_ERROR;
                    }
                }
                else if (i == 2) {
                    while (wrongAnswer == ans || wrongAnswer == answers.get(0) || wrongAnswer == answers.get(1)) {
                        wrongAnswer = ans + rand.nextInt(MAX_ERROR - MIN_ERROR) + MIN_ERROR;
                    }
                }
                else if (i == 3) {
                    while (wrongAnswer == ans || wrongAnswer == answers.get(0) || wrongAnswer == answers.get(1) || wrongAnswer == answers.get(2)) {
                        wrongAnswer = ans + rand.nextInt(MAX_ERROR - MIN_ERROR) + MIN_ERROR;
                    }
                }
                else {
                    while (wrongAnswer == ans) {
                        wrongAnswer = ans + rand.nextInt(MAX_ERROR - MIN_ERROR) + MIN_ERROR;
                    }
                }
                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);
        gridLayout = findViewById(R.id.gridLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}