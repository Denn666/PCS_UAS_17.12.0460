package com.pcs.uas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pcs.uas.model.Data;
import com.pcs.uas.Detail;
import com.pcs.uas.R;

import java.util.ArrayList;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ListViewHolder> {
    private Context mContext;
    private ArrayList<Data> listData;

    public UpcomingAdapter(ArrayList<Data> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UpcomingAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_upcoming,parent,false);
        return new UpcomingAdapter.ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.ListViewHolder holder, int position) {
        Data mov = listData.get(position);

        holder.txtTitle.setText(mov.getTitle());
        holder.txtDate.setText(mov.getDate());

        Glide.with(mContext).load(mov.getImage()).into(holder.previewImage);


        holder.btnLihat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Detail.class);
                intent.putExtra("matchId",mov.getScore());
                mContext.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDate;

        Button btnLihat2;

        ImageView previewImage;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);

            btnLihat2 = itemView.findViewById(R.id.btn_lihat2);
            previewImage = itemView.findViewById(R.id.imgPoster);

        }
    }
}