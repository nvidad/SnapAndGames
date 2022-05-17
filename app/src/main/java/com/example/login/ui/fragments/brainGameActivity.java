package com.example.login.ui.fragments;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.login.R;

import java.util.ArrayList;
import java.util.Random;

public class brainGameActivity extends Fragment {
    Button brainGameGoButton;
    ArrayList<Integer> answers= new ArrayList<Integer>();//store all the posibble answer
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestion=0;
    TextView scoreTextView;
    Button button0 ;//buttons for choosing answers
    Button button1 ;
    Button button2 ;
    Button button3 ;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;



    public brainGameActivity() {

    }

    public void playAgainClicked(View view){
        score=0;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
        resultTextView.setText("Correct!");
        score++;
        }else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        newQuestion();
    }

    public void startClicked(View view){
    brainGameGoButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgainClicked(getView().findViewById(R.id.timerTextView));

    }

    public void newQuestion(){
        Random rand = new Random();//declaring two random numbers between 1=20
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));//viewing text of the sum question

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();//wipe out array

        for (int i = 0; i < 4; i++) {//run 4 times adding something to array
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_brain_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sumTextView = getView().findViewById(R.id.sumTextView);
        button0 = getView().findViewById(R.id.button0);//buttons for choosing answers
        button1 = getView().findViewById(R.id.button1);
        button2 = getView().findViewById(R.id.button2);
        button3 = getView().findViewById(R.id.button3);
        resultTextView = getView().findViewById(R.id.resultTextView);
        scoreTextView = getView().findViewById(R.id.scoreTextView);
        timerTextView = getView().findViewById(R.id.timerTextView);
        playAgainButton = getView().findViewById(R.id.playAgainButton);
        gameLayout = getView().findViewById(R.id.gameLayout);
        brainGameGoButton = getView().findViewById(R.id.brainGameGoButton);

        button0.setOnClickListener(this::chooseAnswer);
        button1.setOnClickListener(this::chooseAnswer);
        button2.setOnClickListener(this::chooseAnswer);
        button3.setOnClickListener(this::chooseAnswer);

        brainGameGoButton.setOnClickListener(this::startClicked);
        playAgainButton.setOnClickListener(this::playAgainClicked);


        brainGameGoButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}