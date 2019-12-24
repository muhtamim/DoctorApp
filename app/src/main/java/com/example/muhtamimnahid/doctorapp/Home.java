package com.example.muhtamimnahid.doctorapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Home extends AppCompatActivity {

    private Button doctor_app, drug_directory, Artical, health_recordd,self_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        doctor_app = (Button) findViewById(R.id.doctor_appointment);
        drug_directory = (Button) findViewById(R.id.drugdirectory);
        Artical = (Button) findViewById(R.id.articles);
        health_recordd = (Button) findViewById(R.id.health_record);
        self_test = (Button) findViewById(R.id.selftest);


        doctor_app.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), Activity_Main_Appointment.class);
                startActivity(i);
            }
        });
        Artical.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), Articale_Home.class);
                startActivity(i);
            }
        });
        drug_directory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Intent i = new Intent(getApplicationContext(),Articale_Home.class);
//                startActivity(i);
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Home.this);
                myAlertDialog.setTitle("--- Drug Directory ---");
                myAlertDialog.setMessage("Do you want to download Drug Directory?");
                myAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://drive.google.com/open?id=1cD7s9IVeMiMB9ZViEw0TusXxvnY-4MAQ"));
                        startActivity(intent);
                    }
                });

                myAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        // do something when the Cancel button is clicked
                    }
                });

                myAlertDialog.show();

            }
        });

        health_recordd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), Activity_Main.class);
                startActivity(i);
            }
        });

        self_test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), SelfTest.class);
                startActivity(i);
            }
        });


    }
}
