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
import com.pcs.uas.R;
import com.pcs.uas.Lihat;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ListViewHolder> {


    private Context mContext;
    private ArrayList<Data> listData;

    public MainAdapter(ArrayList<Data> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MainAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ListViewHolder holder, int position) {
        Data mov = listData.get(position);

        holder.txtTitle.setText(mov.getTitle());
        holder.txtDate.setText(mov.getDate());

        Glide.with(mContext).load(mov.getImage()).into(holder.previewImage);


        holder.btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk_lihatdetail = new Intent(mContext, Lihat.class);
                masuk_lihatdetail.putExtra("matchId",mov.getScore());
                mContext.startActivity(masuk_lihatdetail);

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

        Button btnLihat;

        ImageView previewImage;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            btnLihat = itemView.findViewById(R.id.btn_lihat);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);

            previewImage = itemView.findViewById(R.id.imgPoster);



        }

    }
}
