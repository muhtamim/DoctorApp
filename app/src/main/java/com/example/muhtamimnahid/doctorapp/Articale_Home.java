package com.example.muhtamimnahid.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class Articale_Home extends AppCompatActivity {
    CardView health,fitnes,food,baby,womens;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articale_home);
        getSupportActionBar().setTitle("Articles");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        health = (CardView) findViewById(R.id.healthId);
        fitnes = (CardView) findViewById(R.id.fitnessid);
        food = (CardView) findViewById(R.id.foodid);
        baby = (CardView) findViewById(R.id.babyid);
        womens = (CardView) findViewById(R.id.womensid);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Articale_Home.this,ArticalHealth.class);
                startActivity(intent);
            }
        });
        fitnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Articale_Home.this,ArticalFitnes.class);
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Articale_Home.this,ArticalFood.class);
                startActivity(intent);
            }
        });

        baby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Articale_Home.this,ArticalBaby.class);
                startActivity(intent);
            }
        });
        womens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Articale_Home.this,ArticalWomens.class);
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
