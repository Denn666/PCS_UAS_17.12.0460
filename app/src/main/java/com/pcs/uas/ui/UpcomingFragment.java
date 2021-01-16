package com.pcs.uas.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pcs.uas.R;
import com.pcs.uas.adapter.UpcomingAdapter;
import com.pcs.uas.model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpcomingFragment extends Fragment {
    private String[] title = {"Man UTD vs MAN City","Juventus vs Liverpool","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum"};
    private String[] date = {"5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020"};
    private String[] score = {"1 VS 1","2 VS 1","3 VS 0","2 VS 4","4 VS 4","1 VS 2","0 VS 2"};

    private ArrayList<Data> listData;
    private RecyclerView rvUpcoming;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        listData = new ArrayList<>();


        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        String url ="https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray events = obj.getJSONArray("events");
                            for(int x=0;x<events.length();x++){
                                JSONObject item = events.getJSONObject(x);
                                String matchTitle = item.getString("strEvent");
                                String date = item.getString("dateEvent");
                                String awayScore = item.getString("intAwayScore");
                                String homeScore = item.getString("intHomeScore");
                                String matchDescription = item.getString("strFilename");
                                String image = item.getString("strThumb");
                                String matchId = item.getString("idEvent");
                                listData.add(new Data(matchTitle,date,matchId,awayScore,homeScore,image));


                                System.out.println(matchTitle);


                                rvUpcoming = view.findViewById(R.id.rv_upcoming);
                                rvUpcoming.setHasFixedSize(true);

                                rvUpcoming.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                UpcomingAdapter UpcomingAdapter = new UpcomingAdapter(listData, view.getContext());
                                rvUpcoming.setAdapter(UpcomingAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });


        queue.add(stringRequest);




        return view;
    }
}
