package com.betx.matka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginProfileActivity extends AppCompatActivity {
    Button btnContinue;
    EditText txtName;
    Activity activity;
    Session session;
    String mobilenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile);
        mobilenumber = getIntent().getStringExtra(Constant.MOBILE);
        activity = LoginProfileActivity.this;
        session = new Session(activity);
        btnContinue = findViewById(R.id.btnContinue);
        txtName = findViewById(R.id.txtName);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtName.getText().toString().equals("")){
                    txtName.setError("empty");
                    txtName.requestFocus();
                }
                else{
                    updateUser();
                }
            }
        });
    }

    private void updateUser() {
        String device_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,mobilenumber);
        params.put(Constant.NAME,txtName.getText().toString().trim());
        params.put(Constant.DEVICE_ID,device_id);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        session.setBoolean("is_logged_in", true);
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));
                        Intent intent = new Intent(activity,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.UPDATE_USER_URL, params,true);
    }
}