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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JodiFastCrossAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;
    Button btnSubmit;
    ArrayList<String> addvalue = new ArrayList<>();
    ArrayList<String> PointsArray = new ArrayList<>();
    ArrayList<String> NumbersArray = new ArrayList<>();
    boolean isOnTextChanged = false;
    boolean isOnTextChanged2 = false;
    int ExpenseFinalTotal = 0;
    int NumberFinalTotal = 0;
    ArrayList<String> newPoints = new ArrayList<>();
    ArrayList<String> newNumbers = new ArrayList<>();
    String TotalPoints = "";
    Session session;
    Spinner spinGame;
    String spinGameName;
    String n1 = "",p1 = "",n2 = "",p2 = "",n3 = "",p3 = "",n4 = "",p4 = "",
            n5 = "",p5 = "",n6 = "",p6 = "",n7 = "",p7 = "",n8 = "",p8 = "",n9 = "",p9 = "",n10 = "",p10 = "";

    public JodiFastCrossAdapter(Activity activity, TextView tvWarning, Button btnSubmit, Spinner spinGame) {
        this.activity = activity;
        this.tvWarning = tvWarning;
        this.btnSubmit = btnSubmit;
        this.spinGame = spinGame;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.jodi_fastcross_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent,  @SuppressLint("RecyclerView")int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        session = new Session(activity);
        spinGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Game game = (Game) adapterView.getSelectedItem();
                spinGameName = game.getGamename();
                //Toast.makeText(activity, ""+game.getGamename(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                newPoints.clear();
                newNumbers.clear();
                if ((p1.equals("") && !n1.equals("")) || (!p1.equals("") && n1.equals(""))
                        || (p2.equals("") && !n2.equals("")) || (!p2.equals("") && n2.equals(""))
                        || (p3.equals("") && !n3.equals("")) || (!p3.equals("") && n3.equals(""))
                        || (p4.equals("") && !n4.equals("")) || (!p4.equals("") && n4.equals(""))
                        || (p5.equals("") && !n5.equals("")) || (!p5.equals("") && n5.equals(""))
                        || (p6.equals("") && !n6.equals("")) || (!p6.equals("") && n6.equals(""))
                        || (p7.equals("") && !n7.equals("")) || (!p7.equals("") && n7.equals(""))
                        || (p8.equals("") && !n8.equals("")) || (!p8.equals("") && n8.equals(""))
                        || (p9.equals("") && !n9.equals("")) || (!p9.equals("") && n9.equals(""))
                        || (p10.equals("") && !n10.equals("")) || (!p10.equals("") && n10.equals(""))){
                    Toast.makeText(activity, "Assign Number or Points Not be Empty", Toast.LENGTH_SHORT).show();

                }else {
                    int totalPoints = 0;
                    int totalNumbers = 0;
                    if (PointsArray.size() > 10){
                        totalPoints = 10;
                    }
                    else {
                        totalPoints = PointsArray.size();
                    }

                    if (NumbersArray.size() > 10){
                        totalNumbers = 10;
                    }
                    else {
                        totalNumbers = NumbersArray.size();
                    }
                    boolean PointsZero = false;



                    for (int i = 0; i < totalPoints; i++){
                        int num = Integer.parseInt(PointsArray.get(i).toString());
                        if (num%5 == 0){
                            newPoints.add(String.valueOf(i));
                        }
                        else {
                            PointsZero = true;
                        }

                    }
                    for (int i = 0; i < totalNumbers; i++){
                        newNumbers.add(NumbersArray.get(i));

                    }
                    if (spinGame.getSelectedItemPosition() != 0 && spinGame.getSelectedItemPosition() != 4){
                        if (PointsZero){
                            Toast.makeText(activity, "Points Should Multiple of 5", Toast.LENGTH_SHORT).show();

                        }else {
                            Log.d("JF_NUM",newNumbers.toString());
                            Log.d("JF_NUM_P",PointsArray.toString());
                            submitGame();
                        }
                    }
                    else {
                        Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        holder.etPoints.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etPoints.getText().toString());
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

                setPointsValue(position,editable.toString());


                    ExpenseFinalTotal = 0;
                    try {
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
//                                    if (num%5 == 0){
//                                        PointsArray.set(inposition1,editable.toString());
//                                    }
//                                    else {
//                                        PointsArray.set(inposition1,"0");
//                                    }
                                    break;
                                }
                            }
                            for (int i = 0; i < PointsArray.size() - 1; i++){
                                int tempTotalExpense = Integer.parseInt(PointsArray.get(i));
                                ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;

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
        holder.etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged2 = true;




            }

            @Override
            public void afterTextChanged(Editable editable) {
                setNumbersValue(position,editable.toString());


                NumberFinalTotal = 0;
                if (isOnTextChanged2){
                    isOnTextChanged2 = false;
                    try {
                        String edit_str = editable.toString();
                        edit_str = edit_str.replaceFirst("^0+(?!$)", "");

                        if (edit_str.equals("")) {
                            edit_str = "0";
                        }

                        int num = Integer.parseInt(edit_str);
                        NumberFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int inposition1 = position;
                            if (i != position) {
                                NumbersArray.add("0");
                            }else {
                                NumbersArray.add("0");
                                NumbersArray.set(inposition1,edit_str);
                                break;
                            }
                        }
                        for (int i = 0; i < NumbersArray.size() - 1; i++){
                            int tempTotalExpense = Integer.parseInt(NumbersArray.get(i));
                            NumberFinalTotal = NumberFinalTotal + tempTotalExpense;
                        }

                    }catch (NumberFormatException e){
                        NumberFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int newposition = position;
                            if (i== newposition){
                                NumbersArray.set(newposition,"0");
                            }

                        }
                        for (int i = 0; i <= NumbersArray.size() - 1; i ++){
                            int tempTotalExpense = Integer.parseInt(NumbersArray.get(i));
                            NumberFinalTotal = NumberFinalTotal + tempTotalExpense;
                        }

                    }

                }
            }


        });

    }

    private void setNumbersValue(int position, String s) {
        if (position == 0){
            n1 = s;
        }
        else if (position == 1){
            n2 = s;
        }
        else if (position == 2){
            n3 = s;
        }
        else if (position == 3){
            n4 = s;
        }
        else if (position == 4){
            n5 = s;
        }
        else if (position == 5){
            n6 = s;
        }
        else if (position == 6){
            n7 = s;
        }
        else if (position == 7){
            n8 = s;
        }
        else if (position == 8){
            n9 = s;
        }
        else if (position == 9){
            n10 = s;
        }

    }

    private void setPointsValue(int position, String s) {
        if (position == 0){
            p1 = s;
        }
        else if (position == 1){
            p2 = s;
        }
        else if (position == 2){
            p3 = s;
        }
        else if (position == 3){
            p4 = s;
        }
        else if (position == 4){
            p5 = s;
        }
        else if (position == 5){
            p6 = s;
        }
        else if (position == 6){
            p7 = s;
        }
        else if (position == 7){
            p8 = s;
        }
        else if (position == 8){
            p9 = s;
        }
        else if (position == 9){
            p10 = s;
        }

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
        params.put(Constant.GAME_METHOD,"fastcross");
        params.put(Constant.POINTS,PointsArray.toString());
        params.put(Constant.NUMBER,newNumbers.toString());
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
        return 10;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final EditText etNumber,etPoints;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            etNumber = itemView.findViewById(R.id.etNumber);
            etPoints = itemView.findViewById(R.id.etPoints);


        }
    }
}

