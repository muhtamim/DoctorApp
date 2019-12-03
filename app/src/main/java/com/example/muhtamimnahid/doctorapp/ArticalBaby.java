package com.example.muhtamimnahid.doctorapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.muhtamimnahid.doctorapp.babyAdapter;
import com.example.muhtamimnahid.doctorapp.baby_model;

public class ArticalBaby extends AppCompatActivity {
    private static final String TAG = "TAG:";
    RecyclerView recyclerView;
    babyAdapter adapter;
    List<baby_model> itemsList = new ArrayList<baby_model>();

    String strUni;

    View emptyView;
    private DatabaseReference mDatabase;



    // Progress Dialog
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articale_baby);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        emptyView = findViewById(R.id.layout_empty_event);
        adapter = new babyAdapter(itemsList, this);

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
        mDatabase.child("babyartical").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    baby_model item = postSnapshot.getValue(baby_model.class);

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
}
