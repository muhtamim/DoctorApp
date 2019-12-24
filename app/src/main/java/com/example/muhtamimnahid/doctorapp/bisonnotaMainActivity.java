package com.example.muhtamimnahid.doctorapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;


public class bisonnotaMainActivity extends AppCompatActivity {

    // Member variables for XML Widgets accessible in all the methods of the MainActivity:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    Toast mToastMessage;
    int mIndex;
    int mScore;
    int mQuestion;

    // Array to store Questions and Answers and retrieve from TrueFalse Model
    private TrueFalse[] mQuestionBank = new TrueFalse [] {
            new TrueFalse(R.string.question_57,false),
            new TrueFalse(R.string.question_58,false),
            new TrueFalse(R.string.question_59,false),
            new TrueFalse(R.string.question_60,false),
            new TrueFalse(R.string.question_61,false),
            new TrueFalse(R.string.question_62,true),
            new TrueFalse(R.string.question_63,false),
            new TrueFalse(R.string.question_64,false),
            new TrueFalse(R.string.question_65,false),
            new TrueFalse(R.string.question_67,false),
            new TrueFalse(R.string.question_68,false),
            new TrueFalse(R.string.question_69,false),
            new TrueFalse(R.string.question_70,false),
            new TrueFalse(R.string.question_71,true),
            new TrueFalse(R.string.question_72,false),
            new TrueFalse(R.string.question_73,true),
            new TrueFalse(R.string.question_74,false),
            new TrueFalse(R.string.question_75,false),
            new TrueFalse(R.string.question_76,false),
            new TrueFalse(R.string.question_77,false),
            new TrueFalse(R.string.question_78,false),
            new TrueFalse(R.string.question_79,false),
            new TrueFalse(R.string.question_80,false),
            new TrueFalse(R.string.question_81,false),
            new TrueFalse(R.string.question_82,false),
            new TrueFalse(R.string.question_83,false),
            new TrueFalse(R.string.question_84,false),
            new TrueFalse(R.string.question_85,false),
            new TrueFalse(R.string.question_86,true)


    };

    // Declaring constants here. Rather than a fixed number, choosing to make it a function
    // of the length of the question bank (the number of questions)
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisonnota);

        getSupportActionBar().setTitle("Depression Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Links Views in XML file to Java code
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mScoreTextView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

        // Restores the 'state' of the app upon screen rotation:
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score "+ mScore + "/" + 60);

        } else {
            mScore = 0;
            mIndex = 0;

        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        // Set True and False buttons to listen for user clicks
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                nextQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                nextQuestion();
            }
        });
    }

    // Method to Display Final Message Based on User Score
    private String finalMessage(int Score) {
        String message;
        if (Score == 60) {
            message = "আর দেরি না করে এখনই একজন স্পেশালিস্ট এর কাছে যেতে হবে। যার কাছে কাউন্সেলিং এর মাধ্যমে আপনার বিষন্নতা কাটিয়ে উঠতে পারবেন।";
        } else if (Score > 50 ) {
            message = "আপনি বিষন্নতা ভুগছেন";
        }
        else if (Score > 35 ) {
            message = "আপনি হালকা বিষন্নতা ভুগছেন";
        } else if (Score > 8 ) {
            message = "আপনি স্বাভাবিক আছেন";
        } else {
            message = "আপনার কোন বিষন্নতা হওয়ার সম্ভাবনা নেই";
        }
        return message.toUpperCase();
    }

    // Method to get and display next question
    private void nextQuestion() {
        // This takes and uses the modulus to determine the Index. Not a division.
        mIndex = (mIndex + 1) % mQuestionBank.length;

        // Alert Dialog which displays at the end of the game
        if (mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(finalMessage(mScore));
            alert.setCancelable(false);
            alert.setMessage("আপনার ফলাফল " + mScore + "");
            alert.setPositiveButton("আবার টেস্ট করুন", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    makeText(getApplicationContext(),"আবার টেস্ট দিন", Toast.LENGTH_SHORT).show();
                    recreate();
                    mProgressBar.setProgress(0);
                    mScore = 0;
                }
            });
            alert.setNegativeButton("আপনার জন্য পরামর্শ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(bisonnotaMainActivity.this,Advise.class);
                    startActivity(intent);
                }});

            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("ফলাফল"+ mScore + "/" + 60);
    }

    private void checkAnswer(boolean userSelection) {

        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        // Can cancel the Toast message if there is one on screen and a new answer
        // has been submitted.
        if (mToastMessage != null) {
            mToastMessage.cancel();
        }

        if (userSelection == correctAnswer) {
            mToastMessage = makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
            mScore += 1;
        } else {
            mToastMessage = makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT);
            mScore += 2;
        }

        mToastMessage.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
        }
        return super.onOptionsItemSelected(item);
    }
}
