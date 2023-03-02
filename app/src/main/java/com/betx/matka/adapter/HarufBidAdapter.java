package com.betx.matka.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betx.matka.R;
import com.betx.matka.helper.Session;
import com.betx.matka.model.HarufBids;

import java.util.ArrayList;

public class HarufBidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    Button btnSubmit;
    Session session;
    final ArrayList<HarufBids> harufBids;

    public HarufBidAdapter(Activity activity, ArrayList<HarufBids> harufBids) {
        this.activity = activity;
        this.harufBids = harufBids;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.haruf_bids_lyt, parent, false);
        return new ItemHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ItemHolder holder = (ItemHolder) holderParent;
        final HarufBids harufBid = harufBids.get(position);
        holder.tvNumber.setText(harufBid.getNumber());
        holder.tvPoints.setText(harufBid.getPoints());
        holder.tvType.setText(harufBid.getGame_type());
        Log.d("HARUFADAPTER",harufBid.getGame_type());
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return harufBids.size();
    }
    static class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvNumber,tvPoints,tvType;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            tvType = itemView.findViewById(R.id.tvType);


        }
    }
}

