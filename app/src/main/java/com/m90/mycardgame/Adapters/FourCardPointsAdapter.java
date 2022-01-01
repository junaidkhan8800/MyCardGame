package com.m90.mycardgame.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Models.FourCardPointModel;
import com.m90.mycardgame.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FourCardPointsAdapter extends RecyclerView.Adapter<FourCardPointsAdapter.ViewHolder> {

    Context c;
    List<FourCardPointModel> list;
    onPointsClickListener onPointsClickListener;

    private int selectedPosition = -1;// no selection by default

    public FourCardPointsAdapter(Context c, List<FourCardPointModel> list, FourCardPointsAdapter.onPointsClickListener onPointsClickListener) {
        this.c = c;
        this.list = list;
        this.onPointsClickListener = onPointsClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.item_points,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_points.setText(list.get(position).getPoints());

        if(selectedPosition == position){
            holder.circleImageView.setAlpha((float) 0.5);
        }
        else{
            holder.circleImageView.setAlpha((float) 1.0);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onPointsClickListener.onPointsClick(position,list.get(position).getPoints());

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

        TextView tv_points;
        CircleImageView circleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_points = itemView.findViewById(R.id.tv_points);
            circleImageView = itemView.findViewById(R.id.circleImageView);

        }
    }

    public interface onPointsClickListener {
        void onPointsClick(int position, String points);
    }

}
