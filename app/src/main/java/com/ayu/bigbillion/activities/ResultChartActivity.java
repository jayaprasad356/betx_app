package com.ayu.bigbillion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.google.gson.Gson;
import com.ayu.bigbillion.R;
import com.ayu.bigbillion.adapter.ResultAdapter;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.model.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ResultChartActivity extends AppCompatActivity {
    Button btnSubmit;
    Spinner spinMonth,spinYear;
    ArrayList<Result> results = new ArrayList<>();
    ResultAdapter resultAdapter;
    Activity activity;

    RecyclerView recyclerView;
    ImageButton back;
    Button btnChoose;
    TextView tvDate;
    int yearSelected;
    int monthSelected;
    String Year = "",Month = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_chart);
        activity = ResultChartActivity.this;
        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        btnChoose = findViewById(R.id.btnChoose);

        spinMonth = findViewById(R.id.spinMonth);
        spinYear = findViewById(R.id.spinYear);
        tvDate = findViewById(R.id.tvDate);

        btnSubmit = findViewById(R.id.btnSubmit);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        resultAdapter = new ResultAdapter(activity, results);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                yearSelected = calendar.get(Calendar.YEAR);
                monthSelected = calendar.get(Calendar.MONTH);

                MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment
                        .getInstance(monthSelected, yearSelected);

                dialogFragment.show(getSupportFragmentManager(), null);
                dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int year, int monthOfYear) {

                        monthOfYear = monthOfYear + 1;
                        Year = ""+year;
                        Month = ""+monthOfYear;
                        tvDate.setText(""+year+" - "+monthOfYear);
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewResults();
            }
        });
    }
    private void viewResults()
    {
        results.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MONTH, Month);
        params.put(Constant.YEAR, Year);
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("RESULTCHART_RES",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);

                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Result group = g.fromJson(jsonObject1.toString(), Result.class);
                                results.add(group);
                            } else {
                                break;
                            }
                        }



                    }

                    else {
                        Log.d("RESULT_RESPONSE",""+jsonObject.getString(Constant.MESSAGE));
                    }
                    recyclerView.setAdapter(resultAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("RESULT_RESPONSE",""+e.getMessage());
                }
            }
        }, activity, Constant.RESULT_LISTS_URL, params, true);
    }
}