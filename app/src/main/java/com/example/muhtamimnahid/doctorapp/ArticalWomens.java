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

import com.example.muhtamimnahid.doctorapp.womensAdapter;
import com.example.muhtamimnahid.doctorapp.womens_model;

public class ArticalWomens extends AppCompatActivity {
    private static final String TAG = "TAG:";
    RecyclerView recyclerView;
    womensAdapter adapter;
    List<womens_model> itemsList = new ArrayList<womens_model>();

    String strUni;

    View emptyView;
    private DatabaseReference mDatabase;



    // Progress Dialog
    private ProgressDialog pDialog;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artical_womens);
        getSupportActionBar().setTitle("নারী স্বাস্থ্য");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        emptyView = findViewById(R.id.layout_empty_event);
        adapter = new womensAdapter(itemsList, this);

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
        mDatabase.child("articalwomens").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   womens_model item = postSnapshot.getValue(womens_model.class);

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

