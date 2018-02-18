package com.onkviz.otazkykviz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button answer1, answer2, answer3, answer4;

    TextView score, question;
    QuestionPersister questionPersister = new QuestionPersister();
    private Questions mQuestions = new Questions();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionLength = mQuestions.mQuestions.length;

    Random r;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        score =  findViewById(R.id.score);
        question = findViewById(R.id.question);

        score.setText("Score:" +mScore);
        try {
            String jason = QuestionPersister.AssetJSONFile("question.json", this);
            this.questionPersister.getAllQuestions(jason);
            updateQuestion(this.questionPersister.getQuestion());
        } catch (IOException io ) {
            Log.d("ERROR", "onCreate: " + io.toString());
        }

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = answer1.getText().toString();
                check(selected);


            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = answer2.getText().toString();
                check(selected);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = answer3.getText().toString();
                check(selected);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = answer4.getText().toString();
                check(selected);
            }
        });


    }

    private void check(String selected) {
        if(selected.equalsIgnoreCase(mAnswer)) {
            mScore++;
            score.setText("Score:" +mScore);
            Question q = this.questionPersister.getQuestion();
            if (q == null)
                gameOver();
            else
                updateQuestion(q);
        } else {
            gameOver();
        }
    }

    private void updateQuestion(Question q) {

        question.setText(q.getQUESTION());
        List<String> answrs = new ArrayList<String>();
        String ans = this.questionPersister.getAnswr(q, answrs);
        answrs.add(ans);
        answer1.setText(ans);
        ans = this.questionPersister.getAnswr(q, answrs);
        answrs.add(ans);
        answer2.setText(ans);
        ans = this.questionPersister.getAnswr(q, answrs);
        answrs.add(ans);
        answer3.setText(ans);
        ans = this.questionPersister.getAnswr(q, answrs);
        answrs.add(ans);
        answer4.setText(ans);

        mAnswer = q.getANSWER();

    }

    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Game Over! Your score is " + mScore + "points.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        })



                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
