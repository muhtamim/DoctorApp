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


public class hopelessMainActivity extends AppCompatActivity {

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
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, false),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, false),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true),
            new TrueFalse(R.string.question_14, false),
            new TrueFalse(R.string.question_15, true),
            new TrueFalse(R.string.question_16, false),
            new TrueFalse(R.string.question_17, false),
            new TrueFalse(R.string.question_18, false),
            new TrueFalse(R.string.question_19, true),
            new TrueFalse(R.string.question_20, false)


    };

    // Declaring constants here. Rather than a fixed number, choosing to make it a function
    // of the length of the question bank (the number of questions)
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hopelessmain);

        getSupportActionBar().setTitle("Hopelessness Test");
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
            mScoreTextView.setText("Score " + mScore + "/" + 40);

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
        if (Score == 40) {
            message = "আর দেরি না করে এখনই একজন স্পেশালিস্ট এর কাছে যেতে হবে। যার কাছে কাউন্সেলিং এর মাধ্যমে আপনার আশাহীনতায় কাটিয়ে উঠতে পারবেন।";
        } else if (Score > 35) {
            message = "আপনি আশাহীনতায় ভুগছেন";
        } else if (Score > 25) {
            message = "আপনি হালকা বিষন্নতা ভুগছেন";
        } else if (Score > 8) {
            message = "আপনি স্বাভাবিক আছেন";
        } else {
            message = "আপনার কোন আশাহীনতায় হওয়ার সম্ভাবনা নেই";
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
                    makeText(getApplicationContext(), "আবার টেস্ট দিন", Toast.LENGTH_SHORT).show();
                    recreate();
                    mProgressBar.setProgress(0);
                    mScore = 0;
                }
            });
            alert.setNegativeButton("আপনার জন্য পরামর্শ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(hopelessMainActivity.this, Advise.class);
                    startActivity(intent);
                }
            });

            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("ফলাফল" + mScore + "/" + 40);
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
