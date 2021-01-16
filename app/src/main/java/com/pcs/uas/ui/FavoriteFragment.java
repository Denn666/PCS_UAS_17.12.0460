package com.pcs.uas.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pcs.uas.adapter.FavoriteAdapter;
import com.pcs.uas.R;
import com.pcs.uas.model.Data;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    private String[] title = {"Man UTD vs MAN City","Juventus vs Liverpool","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum"};
    private String[] date = {"5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020"};
    private String[] score = {"1 VS 1","2 VS 1","3 VS 0","2 VS 4","4 VS 4","1 VS 2","0 VS 2"};
    private RecyclerView rvFavorite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        ArrayList<Data> listData = new ArrayList<Data>();

        final SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase
                ("database_0460", android.content.Context.MODE_PRIVATE,null);

        Cursor res =  mydatabase.rawQuery( "SELECT * FROM Favorit", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            String matchtitle = res.getString(0);
            String date = res.getString(1);
            String matchid = res.getString(2);
            String homescore = res.getString(3);
            String awayscore = res.getString(4);
            String image = res.getString(5);
            listData.add(new Data(matchtitle,date,matchid,homescore,awayscore,image));
            res.moveToNext();
        }

        rvFavorite = view.findViewById(R.id.rv_favorite);
        rvFavorite.setHasFixedSize(true);

        rvFavorite.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FavoriteAdapter FavoriteAdapter = new FavoriteAdapter(listData, view.getContext());
        rvFavorite.setAdapter(FavoriteAdapter);

        return view;
    }
}
