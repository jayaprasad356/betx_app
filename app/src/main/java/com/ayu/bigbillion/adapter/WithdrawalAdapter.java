package com.ayu.bigbillion.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.bigbillion.R;
import com.ayu.bigbillion.helper.Session;
import com.ayu.bigbillion.model.Withdrawal;

import java.util.ArrayList;

public class WithdrawalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    Session session;
    final ArrayList<Withdrawal> withdrawals;

    public WithdrawalAdapter(Activity activity, ArrayList<Withdrawal> withdrawals) {
        this.activity = activity;
        this.withdrawals = withdrawals;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.withdrawal_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ItemHolder holder = (ItemHolder) holderParent;
        final Withdrawal withdrawal = withdrawals.get(position);
        holder.tvDate.setText(withdrawal.getDate_created());
        holder.tvStatus.setText(withdrawal.getStatus());
        holder.tvPoints.setText(withdrawal.getPoints());
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return withdrawals.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvDate,tvPoints,tvStatus;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            tvStatus = itemView.findViewById(R.id.tvStatus);


        }
    }
}

