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
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.pcs.uas.adapter.MainAdapter;
import com.pcs.uas.R;
import com.pcs.uas.model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private String[] title = {"Man UTD vs MAN City","Juventus vs Liverpool","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum","lorem ipsum"};
    private String[] date = {"5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020","5 February 2020"};
    private String[] score = {"1 VS 1","2 VS 1","3 VS 0","2 VS 4","4 VS 4","1 VS 2","0 VS 2"};

    private ArrayList<Data> listData;
    private RecyclerView rvMain;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listData = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        String url ="https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328";


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


                                rvMain = view.findViewById(R.id.rv_main);
                                rvMain.setHasFixedSize(true);

                                rvMain.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                MainAdapter MainAdapter = new MainAdapter(listData,view.getContext());
                                rvMain.setAdapter(MainAdapter);

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





        ImageSlider imageSlider = view.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://resources.premierleague.com/premierleague/photo/2018/04/22/884518a6-e54e-4525-a267-359793ed983f/MCI-Cele.jpg", "Man City win againts Brighton 1 - 0"));
        slideModels.add(new SlideModel("https://d3vlf99qeg6bpx.cloudfront.net/content/uploads/2019/12/07192357/GettyImages.1192519728.jpg", "Man United celebrates their first win"));
        slideModels.add(new SlideModel("https://cdn0-production-images-kly.akamaized.net/UO9WLX3Fu6vY6Kqs_am5PoAztcI=/640x640/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3348269/original/035602700_1610533686-Manchester_United_-_Amad_Diallo__Marcus_Rashford__Edwin_van_der_Sar__Gerard_Pique.jpg", "Derby now incoming"));
        imageSlider.setImageList(slideModels, true);

        return view;
    }



}
