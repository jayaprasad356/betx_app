package com.ayu.bigbillion.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.ayu.bigbillion.HomeActivity;
import com.ayu.bigbillion.R;
import com.ayu.bigbillion.activities.JodiActivity;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Session;
import com.ayu.bigbillion.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JodiCrossAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;
    Button btnSubmit;
    ArrayList<String> PointsArray = new ArrayList<>();
    ArrayList<String> NumbersArray = new ArrayList<>();
    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;
    Session session;
    Spinner spinGame;
    String TotalPoints = "";
    String spinGameName;

    public JodiCrossAdapter(Activity activity, TextView tvWarning, Button btnSubmit, Spinner spinGame) {
        this.activity = activity;
        this.tvWarning = tvWarning;
        this.btnSubmit = btnSubmit;
        this.spinGame = spinGame;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.jodi_cross_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        session = new Session(activity);
        final ItemHolder holder = (ItemHolder) holderParent;
        NumberFormat f = new DecimalFormat("00");
        long time = position;
        holder.tvtitle.setText(f.format(time));
        spinGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Game game = (Game) adapterView.getSelectedItem();
                spinGameName = game.getGamename();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                boolean PointsZero = false;
                Log.d("JODI_ARRAY",PointsArray.toString());
                NumbersArray.clear();
                for (int i = 0; i < PointsArray.size(); i++){

                    int num = Integer.parseInt(PointsArray.get(i).toString());
                    if (num%5 == 0){
                        NumbersArray.add(String.valueOf(i));

                    }
                    else {
                        PointsZero = true;

                    }
                }


                if (spinGame.getSelectedItemPosition() != 0  && spinGame.getSelectedItemPosition() != 4){
                    if (PointsZero){
                        Toast.makeText(activity, "Enter number like 10,15,20...", Toast.LENGTH_SHORT).show();

                    }else {
                        submitGame();

                    }


                }

                else {
                    Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                }

            }
        });


        holder.etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;

                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etNumber.getText().toString());
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
            public void afterTextChanged(Editable edt) {
                String editable = edt.toString().replaceFirst("^0+(?!$)", "");
                ExpenseFinalTotal = 0;

                try{
                    String edit_str = editable.toString();
                    if (edit_str.equals("")) {
                        edit_str = "0";
                    }

                    int num = Integer.parseInt(edit_str);
                    try {
                        ExpenseFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int inposition1 = position;
                            if (i != position) {
                                PointsArray.add("0");
                            }else {
                                PointsArray.add("0");
                                PointsArray.set(inposition1,editable.toString());

                                break;
                            }
                        }
                        Log.d("JODI_STORE",""+PointsArray.toString());
                        Log.d("JODI_SIZE",""+PointsArray.size());
                        for (int i = 0; i < PointsArray.size() - 1; i++){
                            int tempTotalExpense = Integer.parseInt(PointsArray.get(i));
                            ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;
                            Log.d("JODI_FOR",""+tempTotalExpense);
                        }
                        TotalPoints = ""+ExpenseFinalTotal;
                        ((JodiActivity)activity).setTotal(ExpenseFinalTotal);
                    }catch (NumberFormatException e){
                        ExpenseFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int newposition = position;
                            if (i== newposition){
                                PointsArray.set(newposition,"0");
                            }
                        }
                        for (int i = 0; i <= PointsArray.size() - 1; i ++){
                            int tempTotalExpense = Integer.parseInt(PointsArray.get(i));
                            ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;
                        }
                        TotalPoints = ""+ExpenseFinalTotal;
                        ((JodiActivity)activity).setTotal(ExpenseFinalTotal);
                    }
                }catch (Exception e){
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void submitGame()
    {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.GAME_TYPE,"jodi");
        params.put(Constant.GAME_METHOD,"cross");
        params.put(Constant.POINTS,PointsArray.toString());
        params.put(Constant.NUMBER,NumbersArray.toString());
        params.put(Constant.TOTAL_POINTS,TotalPoints);
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


    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final EditText etNumber;
        final TextView tvtitle;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            etNumber = itemView.findViewById(R.id.etNumber);
            tvtitle = itemView.findViewById(R.id.tvtitle);


        }
    }
}

