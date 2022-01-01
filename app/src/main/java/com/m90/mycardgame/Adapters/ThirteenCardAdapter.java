package com.m90.mycardgame.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Models.FourCardModel;
import com.m90.mycardgame.R;

import java.util.List;

public class ThirteenCardAdapter extends RecyclerView.Adapter<ThirteenCardAdapter.ViewHolder> {

    Context c;
    List<FourCardModel> list;
    OnTCardClickListener onTCardClickListener;

    private int selectedPosition = -1;// no selection by default

    public ThirteenCardAdapter(Context c, List<FourCardModel> list, OnTCardClickListener onTCardClickListener) {
        this.c = c;
        this.list = list;
        this.onTCardClickListener = onTCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.item_card_thirteen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImageView());

        if (selectedPosition == position) {
            holder.imageView.setAlpha((float) 0.5);
        } else {
            holder.imageView.setAlpha((float) 1.0);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onTCardClickListener.onTCardClick(position);

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

    public interface OnTCardClickListener {
        void onTCardClick(int position);
    }
}
