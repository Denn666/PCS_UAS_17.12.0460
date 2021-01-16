package com.pcs.uas;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Lihat extends AppCompatActivity  implements View.OnClickListener {
    String strPreviewImage;
    private ImageView return_lihat, previewImage;
    private TextView tvkandang, tvlawan, tvtanggal, tvjam, tvskorhome, tvskoraway, tvliga, tvseason, tvtuanrumah, tvstadion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        return_lihat = findViewById(R.id.iv_return);
        return_lihat.setOnClickListener(this);

        previewImage = findViewById(R.id.iv_lihat1);
        tvkandang = findViewById(R.id.iv_kandang);
        tvlawan = findViewById(R.id.iv_lawan);
        tvtanggal = findViewById(R.id.tv_tanggal);
        tvjam = findViewById(R.id.tv_jam);
        tvskorhome = findViewById(R.id.skorhome);
        tvskoraway = findViewById(R.id.skoraway);
        tvliga = findViewById(R.id.liga);
        tvseason = findViewById(R.id.season);
        tvtuanrumah = findViewById(R.id.tuanrumah);
        tvstadion = findViewById(R.id.stadion);

        final String id = getIntent().getStringExtra("matchId");



        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id="+id;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray events = obj.getJSONArray("events");
                            JSONObject e = events.getJSONObject(0);

                            String image = e.getString("strThumb");
                            String iv_kandang = e.getString("strHomeTeam");
                            String iv_lawan = e.getString("strAwayTeam");
                            String tanggal = e.getString("dateEvent");
                            String waktu = e.getString("strTime");
                            String skorhome = e.getString("intHomeScore");
                            String skoraway = e.getString("intAwayScore");
                            String liga = e.getString("strLeague");
                            String season = e.getString("strSeason");
                            String tuanrumah = e.getString("strHomeTeam");
                            String stadion = e.getString("strVenue");
                            strPreviewImage = image;

                            tvkandang.setText(iv_kandang);
                            tvlawan.setText(iv_lawan);
                            tvtanggal.setText(tanggal);
                            tvjam.setText(waktu);
                            tvskorhome.setText(skorhome);
                            tvskoraway.setText(skoraway);
                            tvliga.setText(liga);
                            tvseason.setText(season);
                            tvtuanrumah.setText(tuanrumah);
                            tvstadion.setText(stadion);

                            Glide.with(getApplicationContext()).load(image).into(previewImage);


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


    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_return) {
            Intent balik_home = new Intent(Lihat.this, MainActivity.class);
            startActivity(balik_home);
        }

    }


}
