package com.m90.mycardgame.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Models.MyGameHistoryModel;
import com.m90.mycardgame.R;

import java.util.List;

public class MyGameHistoryAdapter extends RecyclerView.Adapter<MyGameHistoryAdapter.ViewHolder> {

    Context c;
    List<MyGameHistoryModel> list;

    public MyGameHistoryAdapter(Context c, List<MyGameHistoryModel> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gamehistory,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_time.setText(list.get(position).getTime());

/*
        viewHolder.tv_date.setText(String.valueOf(item.created));
        viewHolder.tv_time.setText(String.valueOf(item.created));
*/
        if (list.get(position).getGameType().equals("4")){

            if (list.get(position).getSelectedCard() == 1) {
                holder.cardWon.setBackgroundResource(R.drawable.ic_cards_leaf);
            }
            if (list.get(position).getSelectedCard() == 2) {
                holder.cardWon.setBackgroundResource(R.drawable.ic_asset);
            }
            if (list.get(position).getSelectedCard() == 3) {
                holder.cardWon.setBackgroundResource(R.drawable.ic_cards_spade);
            }
            if (list.get(position).getSelectedCard() == 4) {
                holder.cardWon.setBackgroundResource(R.drawable.ic_cards_diamond);
            }

        }else{

            if (list.get(position).getGameType().equals("13")) {
                holder.cardWon.setBackgroundResource(R.drawable.newcarda);
            }
            if (list.get(position).getSelectedCard() == 2) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard2);
            }
            if (list.get(position).getSelectedCard() == 3) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard3);
            }
            if (list.get(position).getSelectedCard() == 4) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard4);
            }
            if (list.get(position).getSelectedCard() == 5) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard5);
            }
            if (list.get(position).getSelectedCard() == 6) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard6);
            }
            if (list.get(position).getSelectedCard() == 7) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard7);
            }
            if (list.get(position).getSelectedCard() == 8) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard8);
            }
            if (list.get(position).getSelectedCard() == 9) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard9);
            }
            if (list.get(position).getSelectedCard() == 10) {
                holder.cardWon.setBackgroundResource(R.drawable.newcard10);
            }
            if (list.get(position).getSelectedCard() == 11) {
                holder.cardWon.setBackgroundResource(R.drawable.newcardjack);
            }
            if (list.get(position).getSelectedCard() == 12) {
                holder.cardWon.setBackgroundResource(R.drawable.newcardqueen);
            }
            if (list.get(position).getSelectedCard() == 13) {
                holder.cardWon.setBackgroundResource(R.drawable.newcardking);
            }

        }


         if (list.get(position).getInGame() == true){
            holder.tv_winnerText.setVisibility(View.GONE);
            holder.tv_looserText.setVisibility(View.GONE);
            holder.img_trophy.setVisibility(View.GONE);
        }else{

             if(list.get(position).getIsWinner() == true)
             {
                 holder.tv_winnerText.setVisibility(View.VISIBLE);
                 holder.tv_looserText.setVisibility(View.GONE);
                 holder.img_trophy.setVisibility(View.VISIBLE);

             }
             else if (list.get(position).getIsWinner() == false){
                 holder.tv_winnerText.setVisibility(View.GONE);
                 holder.tv_looserText.setVisibility(View.VISIBLE);
                 holder.img_trophy.setVisibility(View.GONE);
             }

         }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date,tv_time,tv_winnerText,tv_looserText;
        ImageView img_trophy, cardWon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_winnerText = itemView.findViewById(R.id.tv_winnerText);
            tv_looserText = itemView.findViewById(R.id.tv_looserText);
            img_trophy = itemView.findViewById(R.id.img_trophy);
            cardWon = itemView.findViewById(R.id.img_cardWon);

        }
    }
}
