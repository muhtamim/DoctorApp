package com.example.muhtamimnahid.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Home extends AppCompatActivity {

    private Button doctor_app,reminder,Artical,health_recordd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        doctor_app = (Button) findViewById(R.id.doctor_appointment);
        reminder = (Button)findViewById(R.id.reminder);
        Artical = (Button) findViewById(R.id.articles);
        health_recordd = (Button) findViewById(R.id.health_record);


        doctor_app.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Activity_Main_Appointment.class);
                startActivity(i);
            }
        });
        Artical.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Articale_Home.class);
                startActivity(i);
            }
        });
        reminder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Articale_Home.class);
                startActivity(i);
            }
        });

        health_recordd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Activity_Main.class);
                startActivity(i);
            }
        });


    }
}
