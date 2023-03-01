package com.ayu.bigbillion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ayu.bigbillion.adapter.WithdrawalAdapter;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Session;
import com.ayu.bigbillion.model.Withdrawal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WithdrawalActivity extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    WithdrawalAdapter withdrawalAdapter;
    Activity activity;
    Session session;
    Button btnWithdrawal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        activity = WithdrawalActivity.this;

        back = findViewById(R.id.back);
        session = new Session(activity);
        recyclerView = findViewById(R.id.recyclerView);
        btnWithdrawal = findViewById(R.id.btnWithdrawal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
        withdrawalList();
        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawalActivity.this,AddWithdrawalActivity.class);
                startActivity(intent);

            }
        });


    }

    private void withdrawalList() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Withdrawal> withdrawals = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Withdrawal group = g.fromJson(jsonObject1.toString(), Withdrawal.class);
                                withdrawals.add(group);
                            } else {
                                break;
                            }
                        }
                        withdrawalAdapter = new WithdrawalAdapter(activity, withdrawals);
                        recyclerView.setAdapter(withdrawalAdapter);
                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.WITHDRAWALLISTS_URL, params, true);
    }
}