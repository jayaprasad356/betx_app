package com.betx.matka;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddWithdrawalActivity extends AppCompatActivity {
    ImageButton back;
    Button btnWithdrawal;
    EditText etPoint;
    Activity activity;
    Session session;
    TextView tvMimWithdrawal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_withdrawal);

        activity = AddWithdrawalActivity.this;
        session = new Session(activity);

        Log.d("SESSION_VALUE",session.getData(Constant.ID));


        back = findViewById(R.id.back);
        btnWithdrawal = findViewById(R.id.btnWithdrawal);
        etPoint = findViewById(R.id.etPoint);
        tvMimWithdrawal = findViewById(R.id.tvMimWithdrawal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPoint.getText().toString().equals(""))
                {
                    etPoint.setError("empty");
                    etPoint.requestFocus();
                }
                else if (etPoint.getText().toString().equals("0"))
                {
                    etPoint.setError("Enter Valid Points");
                    etPoint.requestFocus();
                }


                else if (session.getData(Constant.ACCOUNT_NO).equals("") && session.getData(Constant.PHONEPE).equals("") && session.getData(Constant.PAYTM).equals("")){
                    Toast.makeText(activity, "Fill Account Details Before Withdrawal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity,Add_Account_Details_Activity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    addWithdrawals();
                }
            }
        });
    }

    private void addWithdrawals()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.AMOUNT,etPoint.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("WITHDRAWAL_RES",response);
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
        }, activity, Constant.WITHDRAWAL_URL, params,true);
    }
}