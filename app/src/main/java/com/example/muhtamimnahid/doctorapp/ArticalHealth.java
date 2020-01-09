package com.example.muhtamimnahid.doctorapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.muhtamimnahid.doctorapp.healthAdapter;
import com.example.muhtamimnahid.doctorapp.health_model;

public class ArticalHealth extends AppCompatActivity {
    private static final String TAG = "TAG:";
    RecyclerView recyclerView;
    healthAdapter adapter;
    List<health_model> itemsList = new ArrayList<health_model>();

    String strUni;

    View emptyView;
    private DatabaseReference mDatabase;



    // Progress Dialog
    private ProgressDialog pDialog;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artical_health);
        getSupportActionBar().setTitle("স্বাস্থ্য পরামর্শ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        emptyView = findViewById(R.id.layout_empty_event);
        adapter = new healthAdapter(itemsList, this);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void addData() {
        pDialog = new ProgressDialog(
                this);
        pDialog.setMessage("Loading Data ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        mDatabase.child("articalhealth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    health_model item = postSnapshot.getValue(health_model.class);

                    itemsList.add(item);
                    Log.d(TAG, "Item name: " + item.getName() + ", Image Link " + item.getImageUrl());
                    adapter.notifyDataSetChanged();
                    pDialog.dismiss();
                    emptyView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
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

