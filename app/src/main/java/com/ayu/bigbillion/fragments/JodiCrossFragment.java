package com.ayu.bigbillion.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ayu.bigbillion.R;
import com.ayu.bigbillion.activities.JodiActivity;
import com.ayu.bigbillion.adapter.JodiCrossAdapter;
import com.ayu.bigbillion.helper.Functions;

public class JodiCrossFragment extends Fragment {
    View root;

    Activity activity;
    RecyclerView recyclerView;
    JodiCrossAdapter jodiCrossAdapter;

    TextView tvWarning;
    Button btnSubmit;
    Spinner spinGame;
    String spinGameName;


    public JodiCrossFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_jodi_cross, container, false);

        tvWarning = root.findViewById(R.id.tvWarning);
        spinGame = root.findViewById(R.id.spinGame);
        recyclerView = root.findViewById(R.id.recyclerView);
        btnSubmit = root.findViewById(R.id.btnSubmit);
        activity = getActivity();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        jodiCrossAdapter = new JodiCrossAdapter(activity,tvWarning,btnSubmit,spinGame);

       Functions.setData(activity,spinGame);


        recyclerView.setAdapter(jodiCrossAdapter);

        ((JodiActivity)activity).setTotal(0);




        return root;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            try {
                ((JodiActivity)activity).setTotal(0);

            }catch (Exception e){

            }
        } else {

        }
    }


}