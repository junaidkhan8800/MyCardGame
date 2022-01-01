package com.m90.mycardgame.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Models.FourCardModel;
import com.m90.mycardgame.R;

import java.util.List;

public class FourCardAdapter extends RecyclerView.Adapter<FourCardAdapter.ViewHolder> {

    Context c;
    List<FourCardModel> list;
    OnCardClickListener onCardClickListener;

    private int selectedPosition = -1;// no selection by default

    public FourCardAdapter(Context c, List<FourCardModel> list, OnCardClickListener onCardClickListener) {
        this.c = c;
        this.list = list;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImageView());

        if(selectedPosition == position){
            holder.imageView.setAlpha((float) 0.5);
        }
        else{
            holder.imageView.setAlpha((float) 1.0);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onCardClickListener.onCardClick(position);

                selectedPosition = position;
                notifyDataSetChanged();

                //Toast.makeText(c, "Clicked "+position, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_rvItem);

        }
    }

    public interface OnCardClickListener {
        void onCardClick(int position);
    }
}
