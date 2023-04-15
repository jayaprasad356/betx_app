package com.betx.matka;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betx.matka.activities.HarufActivity;
import com.betx.matka.activities.JodiActivity;
import com.betx.matka.activities.OddEvenActivity;
import com.betx.matka.activities.QuickCrossActivity;
import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.helper.Session;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GamesFragment extends Fragment {
    View root;

    ImageView lytJodi;
    ImageView lytHaruf;
    LinearLayout lytTransaction;
    ImageView lytQuickcross;
    ImageView lytOddeven;
    LinearLayout lytBits;
    ImageView lytWithdrawal;
    ImageView lytDeposit;
    LinearLayout lytSharepoints;
    TextView tvName,tvId,tvMobile;
    TextView tvPoints,tvPhone,newsInfo;
    Session session;
    ImageView play,whatsapp;




    public GamesFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_games, container, false);
        session = new Session(getActivity());


        lytJodi = root.findViewById(R.id.lytJodi);
        tvName = root.findViewById(R.id.tvName);
        tvPoints = root.findViewById(R.id.tvPoints);
        lytDeposit = root.findViewById(R.id.lytDeposit);
        lytHaruf = root.findViewById(R.id.lytHaruf);
        lytQuickcross = root.findViewById(R.id.lytQuickcross);
        lytOddeven = root.findViewById(R.id.lytOddeven);
        lytWithdrawal = root.findViewById(R.id.lytWithdrawal);
        tvPhone = root.findViewById(R.id.tvPhone);
        play = root.findViewById(R.id.play);
        whatsapp = root.findViewById(R.id.whatsapp);
        tvId=root.findViewById(R.id.tv_id);
        tvMobile=root.findViewById(R.id.tv_mobile_number);
        newsInfo = root.findViewById(R.id.newsInfo);
        tvId.setText("Id: "+session.getData(Constant.ID));
        tvMobile.setText("Mobile: "+session.getData(Constant.MOBILE));
        tvName.setText(session.getData(Constant.NAME));
        tvPoints.setText(session.getData(Constant.POINTS));
        newsInfo.setText(session.getData(Constant.NEWS_INFO));

        lytJodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), JodiActivity.class);
                startActivity(intent);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = session.getData(Constant.WHATSAPP_NUMBER);
                String url = "https://api.whatsapp.com/send?phone=+91"+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(session.getData(Constant.YOUTUBE_LINK)));
                startActivity(webIntent);
            }
        });



        lytDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),AddPointsActivity.class);
                startActivity(intent);
            }
        });



        lytHaruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HarufActivity.class);
                startActivity(intent);
            }
        });





        lytQuickcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , QuickCrossActivity.class);
                startActivity(intent);
            }
        });



        lytOddeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OddEvenActivity.class);
                startActivity(intent);
            }
        });



        lytWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WithdrawalActivity.class);
                startActivity(intent);
            }
        });
        setText(session.getData(Constant.POINTS));




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        settings();
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
                        tvPhone.setText(session.getData(Constant.WHATSAPP_NUMBER));
                    }
                    else {
                        //Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }

            //pass url
        }, getActivity(), Constant.SETTINGS_URL, params,false);
    }

    public void setText(String data) {
        tvPoints.setText(data);
    }


}
