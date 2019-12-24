package com.example.muhtamimnahid.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SelfTest extends AppCompatActivity {

    CardView asahahin,uddag,bisonnota,bmi;
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
