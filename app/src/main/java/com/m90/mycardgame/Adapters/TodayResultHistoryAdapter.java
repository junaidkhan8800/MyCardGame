package com.m90.mycardgame.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Models.TodayResultHistoryModel;
import com.m90.mycardgame.R;

import java.util.List;

public class TodayResultHistoryAdapter extends RecyclerView.Adapter<TodayResultHistoryAdapter.ViewHolder> {

    Context c;
    List<TodayResultHistoryModel> list;
    int gametype;

    public TodayResultHistoryAdapter(Context c, List<TodayResultHistoryModel> list, int gametype) {
        this.c = c;
        this.list = list;
        this.gametype = gametype;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_history, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_resulDate.setText(list.get(position).getStartDate());
        holder.tv_resulttime.setText(list.get(position).getStartTime());

        if(list.get(position).getGameType().equals("4") && gametype == 1) {
            if (list.get(position).getWinnerCard() == 1) {
                holder.img_cardWon.setBackgroundResource(R.drawable.ic_cards_leaf);
            }
            if (list.get(position).getWinnerCard() == 2) {
                holder.img_cardWon.setBackgroundResource(R.drawable.ic_asset);
            }
            if (list.get(position).getWinnerCard() == 3) {
                holder.img_cardWon.setBackgroundResource(R.drawable.ic_cards_spade);
            }
            if (list.get(position).getWinnerCard() == 4) {
                holder.img_cardWon.setBackgroundResource(R.drawable.ic_cards_diamond);
            }
        }

        if(list.get(position).getGameType().equals("13") && gametype == 2) {

            if (list.get(position).getWinnerCard() == 1) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcarda);
            }
            if (list.get(position).getWinnerCard() == 2) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard2);
            }
            if (list.get(position).getWinnerCard() == 3) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard3);
            }
            if (list.get(position).getWinnerCard() == 4) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard4);
            }
            if (list.get(position).getWinnerCard() == 5) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard5);
            }
            if (list.get(position).getWinnerCard() == 6) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard6);
            }
            if (list.get(position).getWinnerCard() == 7) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard7);
            }
            if (list.get(position).getWinnerCard() == 8) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard8);
            }
            if (list.get(position).getWinnerCard() == 9) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard9);
            }
            if (list.get(position).getWinnerCard() == 10) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcard10);
            }
            if (list.get(position).getWinnerCard() == 11) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcardjack);
            }
            if (list.get(position).getWinnerCard() == 12) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcardqueen);
            }
            if (list.get(position).getWinnerCard() == 13) {
                holder.img_cardWon.setBackgroundResource(R.drawable.newcardking);
            }
        }

        System.out.println( list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_resulttime,tv_resulDate;
        ImageView img_cardWon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_resulttime = itemView.findViewById(R.id.tv_resulttime);
            tv_resulDate = itemView.findViewById(R.id.tv_resulDate);

            img_cardWon = itemView.findViewById(R.id.img_cardWon);

        }
    }
}
