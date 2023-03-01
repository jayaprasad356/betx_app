package com.ayu.bigbillion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.bigbillion.HomeActivity;
import com.ayu.bigbillion.R;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Functions;
import com.ayu.bigbillion.helper.Session;
import com.ayu.bigbillion.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OddEvenActivity extends AppCompatActivity {

    EditText tvOdd;
    EditText tvEven;
    TextView tvWarning;
    ImageButton back;
    TextView tvTotal;
    Button btnSubmit;
    ArrayList<String> NumbersArray = new ArrayList<>();
    ArrayList<String> PointsArray = new ArrayList<>();
    Spinner spinGame;
    Activity activity;
    Session session;
    String spinGameName;
    int evenint,oddint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oddeven);

        activity = OddEvenActivity.this;
        session = new Session(activity);

        tvOdd = findViewById(R.id.tvOdd);
        tvEven = findViewById(R.id.tvEven);
        tvWarning = findViewById(R.id.tvWarning);
        back = findViewById(R.id.back);
        tvTotal = findViewById(R.id.tvTotal);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinGame = findViewById(R.id.spinGame);
        Functions.setData(activity,spinGame);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        tvOdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(tvOdd.getText().toString());
                        int ans = num/5;
                        if (num%5 == 0){
                            tvWarning.setVisibility(View.GONE);
                        }
                        else{
                            tvWarning.setVisibility(View.VISIBLE);
                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String val = "";
                if (!s.toString().equals("")){
                    val = s.toString();

                }

                else {
                    val = "0";
                }
                int num = Integer.parseInt(val);
                String even = tvEven.getText().toString();
                if (even.equals("")){
                    even = "0";
                }
                String odd = tvOdd.getText().toString();
                if (odd.equals("")){
                    odd = "0";
                }
                if (num%5 == 0){
                    int value = (Integer.parseInt(even) * 50) + (Integer.parseInt(odd) * 50);
                    tvTotal.setText(""+value);

                }
            }
        });

        tvEven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(tvEven.getText().toString());
                        if (num%5 == 0){
                            tvWarning.setVisibility(View.GONE);
                        }
                        else{
                            tvWarning.setVisibility(View.VISIBLE);
                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String val = "";
                if (!s.toString().equals("")){
                    val = s.toString();

                }

                else {
                    val = "0";
                }
                int num = Integer.parseInt(val);
                String even = tvEven.getText().toString();
                if (even.equals("")){
                    even = "0";
                }
                String odd = tvOdd.getText().toString();
                if (odd.equals("")){
                    odd = "0";
                }
                if (num%5 == 0){
                    int value = (Integer.parseInt(even) * 50) + (Integer.parseInt(odd) * 50);
                    tvTotal.setText(""+value);

                }
            }
        });
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
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tvEven.getText().toString().trim().equals("")){
                    evenint = 0;

                }
                else {
                    evenint = Integer.parseInt(tvEven.getText().toString().trim());


                }
                if (tvOdd.getText().toString().trim().equals("")){
                    oddint = 0;

                }
                else {
                    oddint = Integer.parseInt(tvOdd.getText().toString().trim());

                }



                NumbersArray.clear();
                PointsArray.clear();
                if (spinGame.getSelectedItemPosition() == 0  || spinGame.getSelectedItemPosition() == 4){
                    Toast.makeText(activity, "Please , Select Game", Toast.LENGTH_SHORT).show();
                }
                else if (tvEven.getText().toString().equals("") && tvOdd.getText().toString().equals("")){
                    Toast.makeText(activity, "Enter Value", Toast.LENGTH_SHORT).show();

                }
                else if (evenint%5 != 0 || oddint%5 != 0 ){
                    Toast.makeText(activity, "Enter Value Multiplies of 5", Toast.LENGTH_SHORT).show();

                }
                else {
                    oddarrayadd();
                    evenarrayadd();
                    submitGame();

                }



            }
        });


    }

    private void submitGame()
    {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.GAME_TYPE,"odd_even");
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

    private void oddarrayadd() {
        if (!tvOdd.getText().toString().trim().equals("")){
            for (int i = 0; i < 100; i++){
                if (i % 2 !=0){
                    NumbersArray.add(""+i);
                    PointsArray.add(tvOdd.getText().toString().trim().replaceFirst("^0+(?!$)", ""));

                }


            }

        }

    }

    private void evenarrayadd()
    {
        if (!tvEven.getText().toString().trim().equals("")){
            for (int i = 0; i < 100; i++){
                if (i % 2 ==0){
                    NumbersArray.add(""+i);
                    PointsArray.add(tvEven.getText().toString().trim().replaceFirst("^0+(?!$)", ""));

                }


            }
        }

    }
}

