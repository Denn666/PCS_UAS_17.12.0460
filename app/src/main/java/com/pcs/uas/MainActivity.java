package com.pcs.uas;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.pcs.uas.ui.FavoriteFragment;
import com.pcs.uas.ui.HomeFragment;
import com.pcs.uas.ui.UpcomingFragment;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();

    private ChipNavigationBar btm_navigation;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btm_navigation = findViewById(R.id.botnav_home);

        if(savedInstanceState==null){
            btm_navigation.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment userHomeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, userHomeFragment)
                    .commit();
        }

        btm_navigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.favorite:
                        fragment = new FavoriteFragment();
                        break;
                    case R.id.upcoming:
                        fragment = new UpcomingFragment();
                        break;
                }
                if (fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });

        SQLiteDatabase mydatabase = openOrCreateDatabase("database_0460",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Favorit(matchTitle VARCHAR, date VARCHAR, matchId VARCHAR, homeScore VARCHAR,awayScore VARCHAR, image STRING);");


    }
}