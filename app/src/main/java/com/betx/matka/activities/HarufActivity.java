package com.betx.matka.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.betx.matka.R;
import com.betx.matka.adapter.HarufAdapter;
import com.betx.matka.helper.Functions;

public class HarufActivity extends AppCompatActivity {

    Activity activity;
    RecyclerView recyclerView;
    HarufAdapter harufAdapter;
    EditText etAndar;
    EditText etBahar;
    ImageButton back;
    TextView tvWarning,tvTotal;
    Button btnSubmit;
    Spinner spinGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haruf);

        back = findViewById(R.id.back);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinGame = findViewById(R.id.spinGame);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        etAndar = findViewById(R.id.etAndar);
        etBahar = findViewById(R.id.etBahar);
        tvWarning = findViewById(R.id.tvWarning);
        recyclerView = findViewById(R.id.recyclerView);
        tvTotal = findViewById(R.id.tvTotal);
        activity = HarufActivity.this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        harufAdapter = new HarufAdapter(activity,tvWarning,tvTotal,btnSubmit,spinGame);
        recyclerView.setAdapter(harufAdapter);
        Functions.setData(activity,spinGame);
    }
}