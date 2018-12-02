package com.rajeshrayalasolutions.nearbyresturants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rajeshrayalasolutions.nearbyresturants.Adapters.RestaurantListAdapter;
import com.rajeshrayalasolutions.nearbyresturants.Modal.PlacesDetails_Modal;
import com.rajeshrayalasolutions.nearbyresturants.database.DBHelper;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public ArrayList<PlacesDetails_Modal> details_modal;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        dbHelper=new DBHelper(FavouriteActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        details_modal=new ArrayList<>();
       details_modal= dbHelper.getAllCotacts();
        RestaurantListAdapter adapterStores = new RestaurantListAdapter(getApplicationContext(), details_modal, "");
        recyclerView.setAdapter(adapterStores);
        adapterStores.notifyDataSetChanged();
    }
}
