package com.betx.matka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "ARRARY_NUMBER";
    ArrayList<String> number = new ArrayList<>();
    ArrayList<String> points = new ArrayList<>();
    ArrayList<String> resultnum = new ArrayList<>();
    ArrayList<String> resultpoint = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        number.add("0");
        number.add("2");
        points.add("5");
        points.add("10");
        int position = -1;
        position = number.indexOf("6");

        //Log.d("ARRARY_POINTS",resultpoint.toString());

    }
    private int getNumberPos(String number) {
        return number.indexOf(number);
    }
}