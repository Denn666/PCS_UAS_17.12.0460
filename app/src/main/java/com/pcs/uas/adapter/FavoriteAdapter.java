package com.pcs.uas.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pcs.uas.model.Data;
import com.pcs.uas.Detail;
import com.pcs.uas.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ListViewHolder> {
    private Context mContext;
    private ArrayList<Data> listData;

    public FavoriteAdapter(ArrayList<Data> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favorite,parent,false);
        return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ListViewHolder holder, int position) {
        Data mov = listData.get(position);

        holder.txtTitle.setText(mov.getTitle());
        holder.txtDate.setText(mov.getDate());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SQLiteDatabase mydatabase = mContext.openOrCreateDatabase("database_0460",android.content.Context.MODE_PRIVATE,null);
                mydatabase.execSQL(String.format("DELETE FROM favorit WHERE matchId='%s'",listData.get(position).getScore()));
            }
        });

        Glide.with(mContext).load(mov.getImage()).into(holder.previewImage);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDate;
        ImageButton hapus;
        ImageView previewImage;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            hapus = itemView.findViewById(R.id.imb_hapus);
            previewImage = itemView.findViewById(R.id.imgPoster);



        }
    }
}
