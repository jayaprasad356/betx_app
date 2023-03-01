package com.ayu.bigbillion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button btnContinue;
    EditText etMobile;
    Activity activity;
    Session session;
    TextView tvNumber;
    LinearLayout whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnContinue= findViewById(R.id.btnContinue);
        etMobile= findViewById(R.id.etMobile);
        tvNumber= findViewById(R.id.tvNumber);
        whatsapp= findViewById(R.id.whatsapp);
        activity = LoginActivity.this;
        session = new Session(activity);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobile.getText().toString().equals(""))
                {
                    etMobile.setError("empty");
                    etMobile.requestFocus();
                }
                else if (etMobile.getText().length() !=10){
                    etMobile.setError("invalid");
                    etMobile.requestFocus();
                }
                else
                {
                    Intent intent = new Intent(activity,OTP_Activity.class);
                    intent.putExtra("mobile_number",etMobile.getText().toString());
                    startActivity(intent);
                    finish();
                }

            }

        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = session.getData(Constant.WHATSAPP_NUMBER);
                String url = "https://api.whatsapp.com/send?phone=+91"+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        settings();
    }
    private void login() {
        String device_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);

        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,etMobile.getText().toString().trim());
        params.put(Constant.DEVICE_ID,device_id);
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("LOGINRES",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        if (jsonObject.getBoolean(Constant.Login)){

                            session.setBoolean("is_logged_in", true);
                            JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                            session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                            session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                            session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));

                            Intent intent = new Intent(activity,HomeActivity.class);
                            startActivity(intent);
                            finish();
                            Log.d("LOGIN_RES",response);

                        }else{
                            Intent intent = new Intent(activity,LoginProfileActivity.class);
                            intent.putExtra(Constant.MOBILE,etMobile.getText().toString().trim());
                            startActivity(intent);
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(activity, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    Log.d("LOGIN_RES",e.getMessage());
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.LOGIN_URL, params,true);



    }

    private void settings() {
        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.WHATSAPP_NUMBER,jsonArray.getJSONObject(0).getString(Constant.WHATSAPP_NUMBER));
                        session.setData(Constant.YOUTUBE_LINK,jsonArray.getJSONObject(0).getString(Constant.YOUTUBE_LINK));
                        session.setData(Constant.NEWS_INFO,jsonArray.getJSONObject(0).getString(Constant.NEWS_INFO));
                        tvNumber.setText(session.getData(Constant.WHATSAPP_NUMBER));
                    }
                    else {
                        //Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }

            //pass url
        }, activity, Constant.SETTINGS_URL, params,false);
    }

}