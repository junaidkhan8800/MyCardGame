package com.m90.mycardgame.Adapters;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Models.GetWalletTransactionModel;
import com.m90.mycardgame.Models.WalletModel;
import com.m90.mycardgame.R;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    Context c;
    List<GetWalletTransactionModel> list;

    public WalletAdapter(Context c, List<GetWalletTransactionModel> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.item_wallethistory,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.amount.setText(String.valueOf(list.get(position).getTransactionPoints()));
        holder.date.setText(list.get(position).getDate()+" "+list.get(position).getTime());
        holder.status.setText(list.get(position).getTransactionMsg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView amount, date, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            amount = itemView.findViewById(R.id.tv_wallet_amount);
            date = itemView.findViewById(R.id.tv_wallet_date);
            status = itemView.findViewById(R.id.tv_wallet_status);

        }
    }
}
