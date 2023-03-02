package com.betx.matka;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.betx.matka.adapter.ResultAdapter;
import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.model.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ResultFragment extends Fragment {

    View root;
    Button btnSubmit;
    Spinner spinMonth,spinYear;
    ArrayList<Result> results = new ArrayList<>();
    ResultAdapter resultAdapter;
    Activity activity;

    RecyclerView recyclerView;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_result, container, false);
        activity = getActivity();
        recyclerView = root.findViewById(R.id.recyclerView);

        spinMonth = root.findViewById(R.id.spinMonth);
        spinYear = root.findViewById(R.id.spinYear);

        btnSubmit = root.findViewById(R.id.btnSubmit);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        resultAdapter = new ResultAdapter(activity, results);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewResults();
            }
        });


        return root;
    }

    private void viewResults()
    {
        results.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MONTH, spinMonth.getSelectedItem().toString().trim());
        params.put(Constant.YEAR, spinYear.getSelectedItem().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("RESULT_RESPONSE",response);
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


                        recyclerView.setAdapter(resultAdapter);
                    }
                    else {
                        Log.d("RESULT_RESPONSE",""+jsonObject.getString(Constant.MESSAGE));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("RESULT_RESPONSE",""+e.getMessage());
                }
            }
        }, activity, Constant.RESULT_LISTS_URL, params, true);
    }
}