package com.betx.matka.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betx.matka.R;
import com.betx.matka.model.Result;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Result> results;

    public ResultAdapter(Activity activity, ArrayList<Result> results) {
        this.activity = activity;
        this.results = results;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.results_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final Result result = results.get(position);
        String date = result.getDate();
        String[] separated = date.split("-");
        holder.tvDate.setText(separated[2]);
        if (result.getDS() != null){
            holder.tvDS.setText(result.getDS());

        }
        if (result.getFD() != null){
            holder.tvFD.setText(result.getFD());

        }
        if (result.getGB() != null){
            holder.tvGB.setText(result.getGB());

        }
        if (result.getGL() != null){
            holder.tvGL.setText(result.getGL());

        }



    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }





    @Override
    public int getItemCount() {
        return results.size();
    }


    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvDate,tvDS,tvGB,tvGL,tvFD;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDS = itemView.findViewById(R.id.tvDS);
            tvGB = itemView.findViewById(R.id.tvGB);
            tvGL = itemView.findViewById(R.id.tvGL);
            tvFD = itemView.findViewById(R.id.tvFD);


        }
    }
}

