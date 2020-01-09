package com.example.muhtamimnahid.doctorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Calculate_BMI extends AppCompatActivity {
EditText height,weight;
TextView result;
Button calculate;
LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate__bmi);
        getSupportActionBar().setTitle("বি.এম.আই ক্যালকুলেটর");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        height=(EditText)findViewById(R.id.ht);
        weight=(EditText)findViewById(R.id.wt);
        calculate=(Button)findViewById(R.id.b1);
        result=(TextView)findViewById(R.id.res2);
        ll=(LinearLayout)findViewById(R.id.lll) ;
        ll.setVisibility(View.INVISIBLE);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(height.getText().toString().equalsIgnoreCase("")||weight.getText().toString().equalsIgnoreCase("")) {
                  Toast.makeText(Calculate_BMI.this, "Empty not allowed....", Toast.LENGTH_SHORT).show();
              }
              else{
                  ll.setVisibility(View.VISIBLE);
                  //result.setVisibility(View.VISIBLE);
                  String HeightCm = height.getText().toString();
                  String WeightKgs = weight.getText().toString();

                  double htCm=0.0;
                  double wtKgs=0.0;

                  // using cm and kgs
                  try {

                      htCm = Double.parseDouble(HeightCm);
                      wtKgs = Double.parseDouble(WeightKgs);

                  } catch (Exception e) {
                     System.out.println("Could not parse " + e);
                  }
                  double intHeightCm = htCm / 100;
                  double intWeightKgs = wtKgs;
                  double bmiAm = (double) Math.round(100 * intWeightKgs
                          / (intHeightCm * intHeightCm)) / 100;

                  result.setText("বিএমআই হল.. " + bmiAm);

              }
            }
        });

       //

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

