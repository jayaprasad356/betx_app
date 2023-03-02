package com.betx.matka.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.betx.matka.HomeActivity;
import com.betx.matka.R;
import com.betx.matka.helper.ApiConfig;
import com.betx.matka.helper.Constant;
import com.betx.matka.helper.Session;
import com.betx.matka.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HarufAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;
    boolean AndarisOnTextChanged = false;
    boolean BaharisOnTextChanged = false;
    int AndarFinalTotal = 0;
    int BaharFinalTotal = 0;
    ArrayList<String> AndarPointsArray = new ArrayList<>();
    ArrayList<String> AndarnewNumbers = new ArrayList<>();
    ArrayList<String> BaharPointsArray = new ArrayList<>();
    ArrayList<String> BaharnewNumbers = new ArrayList<>();
    String AndarTotalPoints = "0";
    String BaharTotalPoints = "0";
    TextView tvTotal;
    Button btnSubmit;
    ArrayList<String> AndarnewPoints = new ArrayList<>();
    ArrayList<String> BaharnewPoints = new ArrayList<>();
    Spinner spinGame;
    Session session;
    String spinGameName;

    public HarufAdapter(Activity activity, TextView tvWarning, TextView tvTotal, Button btnSubmit, Spinner spinGame) {
        this.activity = activity;
        this.tvWarning = tvWarning;
        this.tvTotal = tvTotal;
        this.btnSubmit = btnSubmit;
        this.spinGame = spinGame;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.haruf_lyt, parent, false);
        return new ItemHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        session = new Session(activity);
        holder.tvAndar.setText(""+position);
        holder.tvBadar.setText(""+position);
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

        holder.etAndar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AndarisOnTextChanged = true;
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etAndar.getText().toString());
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

                AndarFinalTotal = 0;
                if (AndarisOnTextChanged){
                    AndarisOnTextChanged = false;
                    try {
                        AndarFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int inposition1 = position;
                            if (i != position) {
                                AndarPointsArray.add("0");
                            }else {
                                AndarPointsArray.add("0");
                                AndarPointsArray.set(inposition1,editable.toString());
                                break;
                            }
                        }
                        if (AndarPointsArray.size() == 1){
                            int tempTotalExpense = Integer.parseInt(AndarPointsArray.get(0));
                            AndarFinalTotal = AndarFinalTotal + tempTotalExpense;

                        }
                        else {
                            for (int i = 0; i < AndarPointsArray.size() - 1; i++){
                                int tempTotalExpense = Integer.parseInt(AndarPointsArray.get(i));
                                AndarFinalTotal = AndarFinalTotal + tempTotalExpense;

                            }

                        }


                        AndarTotalPoints = ""+ AndarFinalTotal;
                        int totalsum = Integer.parseInt(AndarTotalPoints) + Integer.parseInt(BaharTotalPoints);
                        tvTotal.setText(""+totalsum);

                    }catch (NumberFormatException e){
                        AndarFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int newposition = position;
                            if (i== newposition){
                                AndarPointsArray.set(newposition,"0");
                            }

                        }
                        for (int i = 0; i <= AndarPointsArray.size() - 1; i ++){
                            int tempTotalExpense = Integer.parseInt(AndarPointsArray.get(i));
                            AndarFinalTotal = AndarFinalTotal + tempTotalExpense;

                        }
                        AndarTotalPoints = ""+ AndarFinalTotal;
                        int totalsum = Integer.parseInt(AndarTotalPoints) + Integer.parseInt(BaharTotalPoints);
                        tvTotal.setText(""+totalsum);

                    }

                }

            }
        });

        holder.etBahar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BaharisOnTextChanged = true;

                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etBahar.getText().toString());
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

                BaharFinalTotal = 0;
                try{
                    String edit_str = editable.toString();
                    if (edit_str.equals("")) {
                        edit_str = "0";
                    }
                    int num = Integer.parseInt(edit_str);
                    try {
                        BaharFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int inposition1 = position;
                            if (i != position) {
                                BaharPointsArray.add("0");
                            }else {
                                BaharPointsArray.add("0");
                                BaharPointsArray.set(inposition1,editable.toString());

                                break;
                            }
                        }
                        if (BaharPointsArray.size() == 1){
                            int tempTotalExpense = Integer.parseInt(BaharPointsArray.get(0));
                            BaharFinalTotal = BaharFinalTotal + tempTotalExpense;

                        }
                        else {
                            for (int i = 0; i < BaharPointsArray.size() - 1; i++){
                                int tempTotalExpense = Integer.parseInt(BaharPointsArray.get(i));
                                BaharFinalTotal = BaharFinalTotal + tempTotalExpense;

                            }

                        }

                        BaharTotalPoints = ""+ BaharFinalTotal;
                        int totalsum = Integer.parseInt(AndarTotalPoints) + Integer.parseInt(BaharTotalPoints);
                        tvTotal.setText(""+totalsum);

                    }catch (Exception e){
                        BaharFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int newposition = position;
                            if (i== newposition){
                                BaharPointsArray.set(newposition,"0");
                            }

                        }
                        for (int i = 0; i <= BaharPointsArray.size() - 1; i ++){
                            int tempTotalExpense = Integer.parseInt(BaharPointsArray.get(i));
                            BaharFinalTotal = BaharFinalTotal + tempTotalExpense;

                        }
                        BaharTotalPoints = ""+ BaharFinalTotal;
                        int totalsum = Integer.parseInt(AndarTotalPoints) + Integer.parseInt(BaharTotalPoints);
                        tvTotal.setText(""+totalsum);

                    }

                }catch (Exception e){

                }
//                if (BaharisOnTextChanged){
//                    BaharisOnTextChanged = false;
//                    try {
//                        BaharFinalTotal = 0;
//                        for (int i = 0; i<= position; i++) {
//                            int inposition1 = position;
//                            if (i != position) {
//                                BaharPointsArray.add("0");
//                            }else {
//                                BaharPointsArray.add("0");
//                                BaharPointsArray.set(inposition1,editable.toString());
//                                break;
//                            }
//                        }
//                        for (int i = 0; i < BaharPointsArray.size() - 1; i++){
//                            int tempTotalExpense = Integer.parseInt(BaharPointsArray.get(i));
//                            BaharFinalTotal = BaharFinalTotal + tempTotalExpense;
//
//                        }
//                        BaharTotalPoints = ""+ BaharFinalTotal;
//                        int totalsum = Integer.parseInt(AndarTotalPoints) + Integer.parseInt(BaharTotalPoints);
//                        tvTotal.setText(""+totalsum);
//
//                    }catch (NumberFormatException e){
//                        BaharFinalTotal = 0;
//                        for (int i = 0; i<= position; i++) {
//                            int newposition = position;
//                            if (i== newposition){
//                                BaharPointsArray.set(newposition,"0");
//                            }
//
//                        }
//                        for (int i = 0; i <= BaharPointsArray.size() - 1; i ++){
//                            int tempTotalExpense = Integer.parseInt(BaharPointsArray.get(i));
//                            BaharFinalTotal = BaharFinalTotal + tempTotalExpense;
//
//                        }
//                        BaharTotalPoints = ""+ BaharFinalTotal;
//                        int totalsum = Integer.parseInt(AndarTotalPoints) + Integer.parseInt(BaharTotalPoints);
//                        tvTotal.setText(""+totalsum);
//
//                    }
//
//                }

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BAHARARR",BaharPointsArray.toString());
                boolean AndarZero = false;
                boolean BaharZero = false;
                AndarnewPoints.clear();
                BaharnewPoints.clear();
                int AndartotalPoints = 0;
                int BahartotalPoints = 0;
                if (AndarPointsArray.size() > 10){
                    AndartotalPoints = 10;
                }
                else {
                    AndartotalPoints = AndarPointsArray.size();
                }
                if (BaharPointsArray.size() > 10){
                    BahartotalPoints = 10;
                }
                else {
                    BahartotalPoints = BaharPointsArray.size();
                }
                for (int i = 0; i < AndartotalPoints; i++){
                    int num = Integer.parseInt(AndarPointsArray.get(i).toString());
                    if (num%5 == 0){
                        AndarnewPoints.add(AndarPointsArray.get(i));
                    }
                    else {
                        AndarZero = true;
                    }


                }
                for (int i = 0; i < AndartotalPoints; i++){
                    AndarnewNumbers.add(""+i);
                }
                for (int i = 0; i < BahartotalPoints; i++){
                    int num = Integer.parseInt(BaharPointsArray.get(i));
                    if (num%5 == 0){
                        BaharnewPoints.add(BaharPointsArray.get(i));
                    }
                    else {
                        BaharZero = true;
                    }
                }
                for (int i = 0; i < BahartotalPoints; i++){
                    BaharnewNumbers.add(""+i);
                }
                if (spinGame.getSelectedItemPosition() != 0 ){
                    if (AndarZero){
                        Toast.makeText(activity, "Enter number like 10,15,20...", Toast.LENGTH_SHORT).show();

                    }
                    else if(BaharZero){
                        Toast.makeText(activity, "Enter number like 10,15,20...", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        if (AndarTotalPoints.equals("0") && BaharTotalPoints.equals("0")){
                            Toast.makeText(activity, "Value is Empty", Toast.LENGTH_SHORT).show();

                        }
                        else if (AndarTotalPoints.equals("0")){
                            submitBaharGame();

                        }
                        else if (BaharTotalPoints.equals("0")){
                            submitAndarGame();

                        }else {
                            submitAndarGame();

                        }

                    }


                }
                else {
                    Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        final TextView tvAndar,tvBadar;
        EditText etAndar,etBahar;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvAndar = itemView.findViewById(R.id.tvAndar);
            tvBadar = itemView.findViewById(R.id.tvBadar);
            etAndar = itemView.findViewById(R.id.etAndar);
            etBahar = itemView.findViewById(R.id.etBahar);


        }
    }
    private void submitAndarGame()
    {
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.GAME_TYPE,"andar");
        params.put(Constant.GAME_METHOD,"none");
        params.put(Constant.POINTS, AndarnewPoints.toString());
        params.put(Constant.NUMBER, AndarnewNumbers.toString());
        params.put(Constant.TOTAL_POINTS, AndarTotalPoints);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        if (BaharTotalPoints.equals("0")){
                            JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                            session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                            session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                            session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));
                            Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity, HomeActivity.class);
                            activity.startActivity(intent);
                            activity.finish();

                        }else {
                            submitBaharGame();
                        }
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
        }, activity, Constant.HARUF_URL, params,true);


    }

    private void submitBaharGame()
    {
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.GAME_TYPE,"bahar");
        params.put(Constant.GAME_METHOD,"none");
        params.put(Constant.POINTS, BaharnewPoints.toString());
        params.put(Constant.NUMBER, BaharnewNumbers.toString());
        params.put(Constant.TOTAL_POINTS, BaharTotalPoints);
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
        }, activity, Constant.HARUF_URL, params,true);


    }
}

