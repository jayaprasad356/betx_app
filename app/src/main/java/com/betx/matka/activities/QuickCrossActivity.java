package com.betx.matka.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.betx.matka.HomeActivity;
import com.betx.matka.R;
import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.helper.Functions;
import com.betx.matka.helper.Session;
import com.betx.matka.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuickCrossActivity extends AppCompatActivity {

    EditText etPoints;
    TextView tvWarning;
    ImageButton back;
    EditText eta1,eta2,eta3,eta4,eta5,eta6;
    EditText etb1,etb2,etb3,etb4,etb5,etb6;
    ArrayList<String> NumbersArray = new ArrayList<>();
    ArrayList<String> PointsArray = new ArrayList<>();
    ArrayList<String> PointsAy = new ArrayList<>();
    Button btnSubmit;
    Activity activity;
    Session session;
    TextView tvTotal;
    Spinner spinGame;
    String spinGameName;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickcross);

        etPoints = findViewById(R.id.etPoints);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvWarning = findViewById(R.id.tvWarning);
        tvTotal = findViewById(R.id.tvTotal);
        spinGame = findViewById(R.id.spinGame);

        back = findViewById(R.id.back);
        eta1 = findViewById(R.id.eta1);
        eta2 = findViewById(R.id.eta2);
        eta3 = findViewById(R.id.eta3);
        eta4 = findViewById(R.id.eta4);
        eta5 = findViewById(R.id.eta5);
        eta6 = findViewById(R.id.eta6);

        etb1 = findViewById(R.id.etb1);
        etb2 = findViewById(R.id.etb2);
        etb3 = findViewById(R.id.etb3);
        etb4 = findViewById(R.id.etb4);
        etb5 = findViewById(R.id.etb5);
        etb6 = findViewById(R.id.etb6);
        activity = QuickCrossActivity.this;
        session = new Session(activity);
        Functions.setData(activity,spinGame);
        spinGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Game game = (Game) adapterView.getSelectedItem();
                spinGameName = game.getGamename();
                //Toast.makeText(activity, ""+game.getGamename(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        etPoints.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(etPoints.getText().toString());
                        if (num%5 == 0){
                            tvWarning.setVisibility(View.GONE);
                            int total = Integer.parseInt(etPoints.getText().toString().trim()) * 36;
                            tvTotal.setText(""+total);
                        }
                        else{
                            tvWarning.setText("Enter number like 10,15,20...");
                            tvWarning.setVisibility(View.VISIBLE);
                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        eta1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkAvalueempty()){
                            AcheckValue();

                        }



                    }


                }catch (Exception e){

                }

            }
        });
        eta2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkAvalueempty()){
                            AcheckValue();

                        }

                    }


                }catch (Exception e){

                }

            }
        });
        eta3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkAvalueempty()){
                            AcheckValue();

                        }


                    }


                }catch (Exception e){

                }

            }
        });
        eta4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkAvalueempty()){
                            AcheckValue();

                        }

                    }


                }catch (Exception e){

                }

            }
        });
        eta5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkAvalueempty()){
                            AcheckValue();

                        }

                    }


                }catch (Exception e){

                }

            }
        });
        eta6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkAvalueempty()){
                            AcheckValue();

                        }

                    }


                }catch (Exception e){

                }

            }
        });
        etb1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkBvalueempty()){
                            BcheckValue();

                        }


                    }


                }catch (Exception e){

                }

            }
        });
        etb2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkBvalueempty()){
                            BcheckValue();

                        }

                    }


                }catch (Exception e){

                }

            }
        });
        etb3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkBvalueempty()){
                            BcheckValue();

                        }


                    }


                }catch (Exception e){

                }

            }
        });
        etb4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkBvalueempty()){
                            BcheckValue();

                        }

                    }


                }catch (Exception e){

                }

            }
        });
        etb5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkBvalueempty()){
                            BcheckValue();

                        }
                    }


                }catch (Exception e){

                }

            }
        });
        etb6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (!editable.toString().equals("")){
                        if (!checkBvalueempty()){
                            BcheckValue();

                        }
                    }


                }catch (Exception e){

                }

            }
        });













        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //view.startAnimation(buttonClick);
                Toast.makeText(activity, ""+spinGame.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if (eta1.getText().toString().trim().equals("") || eta2.getText().toString().trim().equals("")  ||
                        eta3.getText().toString().trim().equals("") || eta4.getText().toString().trim().equals("") ||
                        eta5.getText().toString().trim().equals("") || eta6.getText().toString().trim().equals("") ||
                        etb1.getText().toString().trim().equals("") || etb2.getText().toString().trim().equals("") ||
                        etb3.getText().toString().trim().equals("") || etb4.getText().toString().trim().equals("") ||
                        etb5.getText().toString().trim().equals("") || etb6.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Enter All Details", Toast.LENGTH_SHORT).show();

                }
                else if (eta1.getText().toString().trim().equals("0") || eta2.getText().toString().trim().equals("0")  ||
                        eta3.getText().toString().trim().equals("0") || eta4.getText().toString().trim().equals("0") ||
                        eta5.getText().toString().trim().equals("0") || eta6.getText().toString().trim().equals("0") ||
                        etb1.getText().toString().trim().equals("0") || etb2.getText().toString().trim().equals("0") ||
                        etb3.getText().toString().trim().equals("0") || etb4.getText().toString().trim().equals("0") ||
                        etb5.getText().toString().trim().equals("0") || etb6.getText().toString().trim().equals("0")){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (eta1.getText().toString().trim().equals(eta2.getText().toString().trim()) || eta1.getText().toString().trim().equals(eta3.getText().toString().trim())  ||
                        eta1.getText().toString().trim().equals(eta4.getText().toString().trim()) || eta1.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                        eta1.getText().toString().trim().equals(eta6.getText().toString().trim())
                        ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (eta2.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta2.getText().toString().trim().equals(eta3.getText().toString().trim())  ||
                        eta2.getText().toString().trim().equals(eta4.getText().toString().trim()) || eta2.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                        eta2.getText().toString().trim().equals(eta6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (eta3.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta3.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                        eta3.getText().toString().trim().equals(eta4.getText().toString().trim()) || eta3.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                        eta3.getText().toString().trim().equals(eta6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (eta4.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta4.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                        eta4.getText().toString().trim().equals(eta3.getText().toString().trim()) || eta4.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                        eta4.getText().toString().trim().equals(eta6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (eta5.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta5.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                        eta5.getText().toString().trim().equals(eta3.getText().toString().trim()) || eta5.getText().toString().trim().equals(eta4.getText().toString().trim()) ||
                        eta5.getText().toString().trim().equals(eta6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row" , Toast.LENGTH_SHORT).show();

                }
                else if (eta6.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta6.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                        eta6.getText().toString().trim().equals(eta3.getText().toString().trim()) || eta6.getText().toString().trim().equals(eta4.getText().toString().trim()) ||
                        eta6.getText().toString().trim().equals(eta5.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (etb1.getText().toString().trim().equals(etb2.getText().toString().trim()) || etb1.getText().toString().trim().equals(etb3.getText().toString().trim())  ||
                        etb1.getText().toString().trim().equals(etb4.getText().toString().trim()) || etb1.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                        etb1.getText().toString().trim().equals(etb6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (etb2.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb2.getText().toString().trim().equals(etb3.getText().toString().trim())  ||
                        etb2.getText().toString().trim().equals(etb4.getText().toString().trim()) || etb2.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                        etb2.getText().toString().trim().equals(etb6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (etb3.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb3.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                        etb3.getText().toString().trim().equals(etb4.getText().toString().trim()) || etb3.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                        etb3.getText().toString().trim().equals(etb6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (etb4.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb4.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                        etb4.getText().toString().trim().equals(etb3.getText().toString().trim()) || etb4.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                        etb4.getText().toString().trim().equals(etb6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (etb5.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb5.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                        etb5.getText().toString().trim().equals(etb3.getText().toString().trim()) || etb5.getText().toString().trim().equals(etb4.getText().toString().trim()) ||
                        etb5.getText().toString().trim().equals(etb6.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row" , Toast.LENGTH_SHORT).show();

                }
                else if (etb6.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb6.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                        etb6.getText().toString().trim().equals(etb3.getText().toString().trim()) || etb6.getText().toString().trim().equals(etb4.getText().toString().trim()) ||
                        etb6.getText().toString().trim().equals(etb5.getText().toString().trim())
                ){
                    Toast.makeText(activity, "Number should not be repeated in same row", Toast.LENGTH_SHORT).show();

                }
                else if (etPoints.getText().toString().equals("")){
                    etPoints.setError("Enter Points");
                    etPoints.requestFocus();
                }
                else if (etPoints.getText().toString().equals("0") || Integer.parseInt(etPoints.getText().toString().trim()) % 5 != 0){
                    etPoints.setError("Enter Valid Points");
                    etPoints.requestFocus();
                }
                else if (spinGame.getSelectedItemPosition() == 0  || spinGame.getSelectedItemPosition() == 4){
                    Toast.makeText(activity, "Please , Select Game", Toast.LENGTH_SHORT).show();
                }
                else {
                    NumbersArray.clear();
                    PointsArray.clear();
                    NumbersArray.add(eta1.getText().toString().trim() + etb1.getText().toString().toString().trim());
                    NumbersArray.add(eta1.getText().toString().trim() + etb2.getText().toString().toString().trim());
                    NumbersArray.add(eta1.getText().toString().trim() + etb3.getText().toString().toString().trim());
                    NumbersArray.add(eta1.getText().toString().trim() + etb4.getText().toString().toString().trim());
                    NumbersArray.add(eta1.getText().toString().trim() + etb5.getText().toString().toString().trim());
                    NumbersArray.add(eta1.getText().toString().trim() + etb6.getText().toString().toString().trim());
                    NumbersArray.add(eta2.getText().toString().trim() + etb1.getText().toString().toString().trim());
                    NumbersArray.add(eta2.getText().toString().trim() + etb2.getText().toString().toString().trim());
                    NumbersArray.add(eta2.getText().toString().trim() + etb3.getText().toString().toString().trim());
                    NumbersArray.add(eta2.getText().toString().trim() + etb4.getText().toString().toString().trim());
                    NumbersArray.add(eta2.getText().toString().trim() + etb5.getText().toString().toString().trim());
                    NumbersArray.add(eta2.getText().toString().trim() + etb6.getText().toString().toString().trim());
                    NumbersArray.add(eta3.getText().toString().trim() + etb1.getText().toString().toString().trim());
                    NumbersArray.add(eta3.getText().toString().trim() + etb2.getText().toString().toString().trim());
                    NumbersArray.add(eta3.getText().toString().trim() + etb3.getText().toString().toString().trim());
                    NumbersArray.add(eta3.getText().toString().trim() + etb4.getText().toString().toString().trim());
                    NumbersArray.add(eta3.getText().toString().trim() + etb5.getText().toString().toString().trim());
                    NumbersArray.add(eta3.getText().toString().trim() + etb6.getText().toString().toString().trim());
                    NumbersArray.add(eta4.getText().toString().trim() + etb1.getText().toString().toString().trim());
                    NumbersArray.add(eta4.getText().toString().trim() + etb2.getText().toString().toString().trim());
                    NumbersArray.add(eta4.getText().toString().trim() + etb3.getText().toString().toString().trim());
                    NumbersArray.add(eta4.getText().toString().trim() + etb4.getText().toString().toString().trim());
                    NumbersArray.add(eta4.getText().toString().trim() + etb5.getText().toString().toString().trim());
                    NumbersArray.add(eta4.getText().toString().trim() + etb6.getText().toString().toString().trim());
                    NumbersArray.add(eta5.getText().toString().trim() + etb1.getText().toString().toString().trim());
                    NumbersArray.add(eta5.getText().toString().trim() + etb2.getText().toString().toString().trim());
                    NumbersArray.add(eta5.getText().toString().trim() + etb3.getText().toString().toString().trim());
                    NumbersArray.add(eta5.getText().toString().trim() + etb4.getText().toString().toString().trim());
                    NumbersArray.add(eta5.getText().toString().trim() + etb5.getText().toString().toString().trim());
                    NumbersArray.add(eta5.getText().toString().trim() + etb6.getText().toString().toString().trim());
                    NumbersArray.add(eta6.getText().toString().trim() + etb1.getText().toString().toString().trim());
                    NumbersArray.add(eta6.getText().toString().trim() + etb2.getText().toString().toString().trim());
                    NumbersArray.add(eta6.getText().toString().trim() + etb3.getText().toString().toString().trim());
                    NumbersArray.add(eta6.getText().toString().trim() + etb4.getText().toString().toString().trim());
                    NumbersArray.add(eta6.getText().toString().trim() + etb5.getText().toString().toString().trim());
                    NumbersArray.add(eta6.getText().toString().trim() + etb6.getText().toString().toString().trim());
                    for (int i = 0; i < 36; i++){
                        PointsArray.add(etPoints.getText().toString().trim().replaceFirst("^0+(?!$)", ""));
                    }
                    submitGame();

                }
            }
        });
    }

    private boolean checkAvalueempty() {
        if (eta1.getText().toString().trim().equals("")){
            return true;

        }else if (eta2.getText().toString().trim().equals("")){
            return true;

        }else if (eta3.getText().toString().trim().equals("")){
            return true;

        }else if (eta4.getText().toString().trim().equals("")){
            return true;

        }else if (eta5.getText().toString().trim().equals("")){
            return true;

        }else if (eta6.getText().toString().trim().equals("")){
            return true;

        }
        else {
            return false;

        }

    }
    private boolean checkBvalueempty() {
        if (etb1.getText().toString().trim().equals("")){
            return true;

        }else if (etb2.getText().toString().trim().equals("")){
            return true;

        }else if (etb3.getText().toString().trim().equals("")){
            return true;

        }else if (etb4.getText().toString().trim().equals("")){
            return true;

        }else if (etb5.getText().toString().trim().equals("")){
            return true;

        }else if (etb6.getText().toString().trim().equals("")){
            return true;

        }
        else {
            return false;

        }

    }


    private void BcheckValue() {
        if (etb1.getText().toString().trim().equals(etb2.getText().toString().trim()) || etb1.getText().toString().trim().equals(etb3.getText().toString().trim())  ||
                etb1.getText().toString().trim().equals(etb4.getText().toString().trim()) || etb1.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                etb1.getText().toString().trim().equals(etb6.getText().toString().trim())
        ){
            tvWarning.setVisibility(View.VISIBLE);
            tvWarning.setText("Number should not be repeated in same row");

        }else if (etb2.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb2.getText().toString().trim().equals(etb3.getText().toString().trim())  ||
                etb2.getText().toString().trim().equals(etb4.getText().toString().trim()) || etb2.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                etb2.getText().toString().trim().equals(etb6.getText().toString().trim())
        ){
            tvWarning.setVisibility(View.VISIBLE);
            tvWarning.setText("Number should not be repeated in same row");

        }else if (etb3.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb3.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                etb3.getText().toString().trim().equals(etb4.getText().toString().trim()) || etb3.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                etb3.getText().toString().trim().equals(etb6.getText().toString().trim())
        ){
            tvWarning.setVisibility(View.VISIBLE);
            tvWarning.setText("Number should not be repeated in same row");

        }else if (etb4.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb4.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                etb4.getText().toString().trim().equals(etb3.getText().toString().trim()) || etb4.getText().toString().trim().equals(etb5.getText().toString().trim()) ||
                etb4.getText().toString().trim().equals(etb6.getText().toString().trim())
        ){
            tvWarning.setVisibility(View.VISIBLE);
            tvWarning.setText("Number should not be repeated in same row");

        }else if (etb5.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb5.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                etb5.getText().toString().trim().equals(etb3.getText().toString().trim()) || etb5.getText().toString().trim().equals(etb4.getText().toString().trim()) ||
                etb5.getText().toString().trim().equals(etb6.getText().toString().trim())
        ){
            tvWarning.setVisibility(View.VISIBLE);
            tvWarning.setText("Number should not be repeated in same row");

        }else if (etb6.getText().toString().trim().equals(etb1.getText().toString().trim()) || etb6.getText().toString().trim().equals(etb2.getText().toString().trim())  ||
                etb6.getText().toString().trim().equals(etb3.getText().toString().trim()) || etb6.getText().toString().trim().equals(etb4.getText().toString().trim()) ||
                etb6.getText().toString().trim().equals(etb5.getText().toString().trim())
        ){
            tvWarning.setVisibility(View.VISIBLE);
            tvWarning.setText("Number should not be repeated in same row");

        }
        else{
            tvWarning.setVisibility(View.GONE);

        }

    }


    private void AcheckValue() {
        if (eta1.getText().toString().trim().equals(eta2.getText().toString().trim()) || eta1.getText().toString().trim().equals(eta3.getText().toString().trim())  ||
                eta1.getText().toString().trim().equals(eta4.getText().toString().trim()) || eta1.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                eta1.getText().toString().trim().equals(eta6.getText().toString().trim())
        ){
            tvWarningmsg();
        }
        else if (eta2.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta2.getText().toString().trim().equals(eta3.getText().toString().trim())  ||
                eta2.getText().toString().trim().equals(eta4.getText().toString().trim()) || eta2.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                eta2.getText().toString().trim().equals(eta6.getText().toString().trim())
        ){
            tvWarningmsg();

        }else if (eta3.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta3.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                eta3.getText().toString().trim().equals(eta4.getText().toString().trim()) || eta3.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                eta3.getText().toString().trim().equals(eta6.getText().toString().trim())
        ){
            tvWarningmsg();

        }else if (eta4.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta4.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                eta4.getText().toString().trim().equals(eta3.getText().toString().trim()) || eta4.getText().toString().trim().equals(eta5.getText().toString().trim()) ||
                eta4.getText().toString().trim().equals(eta6.getText().toString().trim())
        ){
            tvWarningmsg();

        }else if (eta5.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta5.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                eta5.getText().toString().trim().equals(eta3.getText().toString().trim()) || eta5.getText().toString().trim().equals(eta4.getText().toString().trim()) ||
                eta5.getText().toString().trim().equals(eta6.getText().toString().trim())
        ){
            tvWarningmsg();

        }else if (eta6.getText().toString().trim().equals(eta1.getText().toString().trim()) || eta6.getText().toString().trim().equals(eta2.getText().toString().trim())  ||
                eta6.getText().toString().trim().equals(eta3.getText().toString().trim()) || eta6.getText().toString().trim().equals(eta4.getText().toString().trim()) ||
                eta6.getText().toString().trim().equals(eta5.getText().toString().trim())
        ){
            tvWarningmsg();
        }
        else{
            tvWarning.setVisibility(View.GONE);

        }
    }

    private void tvWarningmsg()
    {
        tvWarning.setVisibility(View.VISIBLE);
        tvWarning.setText("Number should not be repeated in same row");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void submitGame()
    {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.GAME_TYPE,"quick_cross");
        params.put(Constant.GAME_METHOD,"none");
        params.put(Constant.POINTS,PointsArray.toString());
        params.put(Constant.NUMBER,NumbersArray.toString());
        params.put(Constant.TOTAL_POINTS,tvTotal.getText().toString().trim());
        params.put(Constant.GAME_DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, HomeActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(activity, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.GAME_URL, params,true);


    }
}