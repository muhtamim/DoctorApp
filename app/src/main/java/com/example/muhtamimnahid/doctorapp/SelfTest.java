package com.example.muhtamimnahid.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SelfTest extends AppCompatActivity {

    public CardView asahahin,uddag,bisonnota,bmi,bmical,water;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);
        getSupportActionBar().setTitle("Self Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        asahahin = (CardView) findViewById(R.id.asahinotaId);
        uddag = (CardView) findViewById(R.id.uddagid);
        bisonnota=(CardView)findViewById(R.id.bisonnotaid);
        bmi = (CardView) findViewById(R.id.bmiid);
        bmical = (CardView) findViewById(R.id.bmical);
        water = (CardView) findViewById(R.id.waterid);

       asahahin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfTest.this,hopelessMainActivity.class);
                startActivity(intent);
            }
        });

       uddag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfTest.this,UddakMainActivity.class);
                startActivity(intent);
            }
        });

        bisonnota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfTest.this,bisonnotaMainActivity.class);
                startActivity(intent);
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfTest.this,Calculators.class);
                startActivity(intent);
            }
        });
        bmical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfTest.this,Calculate_BMI.class);
                startActivity(intent);
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfTest.this,Water_Intake.class);
                startActivity(intent);
            }
        });

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
