package com.betx.matka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Add_Account_Details_Activity extends AppCompatActivity {

    ImageButton back;
    EditText etAccountNo, etIFSC, etHolderName, etPaytm,etPhonepe;
    ImageButton btnUpdate;
    Activity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);


        activity = Add_Account_Details_Activity.this;
        session = new Session(activity);

        back = findViewById(R.id.back);
        etPaytm = findViewById(R.id.etPaytm);
        etPhonepe = findViewById(R.id.etPhonepe);
        etAccountNo = findViewById(R.id.etAccountNo);
        etIFSC = findViewById(R.id.etIFSC);
        etHolderName = findViewById(R.id.etHolderName);
        btnUpdate = findViewById(R.id.btnUpdate);


        etAccountNo.setText(session.getData(Constant.ACCOUNT_NO));
        etIFSC.setText(session.getData(Constant.IFSC_CODE));
        etHolderName.setText(session.getData(Constant.HOLDER_NAME));
        etPhonepe.setText(session.getData(Constant.PHONEPE));
        etPaytm.setText(session.getData(Constant.PAYTM));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFillAccount() && etPaytm.getText().toString().trim().equals("") && etPhonepe.getText().toString().trim().equals("")){
                    Toast.makeText(activity, "Should Fill Any One Payment Method", Toast.LENGTH_SHORT).show();

                }
                else if (etAccountNo.getText().toString().trim().equals("") || etIFSC.getText().toString().trim().equals("") || etHolderName.getText().toString().trim().equals("")){


                            Toast.makeText(activity, "Fill Account Details", Toast.LENGTH_SHORT).show();

                }
                    else{
                        updateAccountDetails();
                    }



            }
        });

    }

    private boolean isFillAccount() {
        if (!etAccountNo.getText().toString().trim().equals("") || !etIFSC.getText().toString().trim().equals("") || !etHolderName.getText().toString().trim().equals("")){
            return true;
        }



        return false;
    }


    private void updateAccountDetails() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID, session.getData(Constant.ID));
        params.put(Constant.ACCOUNT_NO, etAccountNo.getText().toString().trim());
        params.put(Constant.IFSC_CODE, etIFSC.getText().toString().trim());
        params.put(Constant.HOLDER_NAME, etHolderName.getText().toString().trim());
        params.put(Constant.PAYTM, etPaytm.getText().toString().trim());
        params.put(Constant.PHONEPE, etPhonepe.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.ACCOUNT_NO, jsonArray.getJSONObject(0).getString(Constant.ACCOUNT_NO));
                        session.setData(Constant.IFSC_CODE, jsonArray.getJSONObject(0).getString(Constant.IFSC_CODE));
                        session.setData(Constant.HOLDER_NAME, jsonArray.getJSONObject(0).getString(Constant.HOLDER_NAME));
                        session.setData(Constant.PAYTM, jsonArray.getJSONObject(0).getString(Constant.PAYTM));
                        session.setData(Constant.PHONEPE, jsonArray.getJSONObject(0).getString(Constant.PHONEPE));
                        Toast.makeText(this, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, String.valueOf(response) + String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.UPDATE_ACCOUNT_DETAILS_URL, params, true);


    }
}