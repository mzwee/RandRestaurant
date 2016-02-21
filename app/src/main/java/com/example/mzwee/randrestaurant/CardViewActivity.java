package com.example.mzwee.randrestaurant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by User on 20-Feb-16.
 */
public class CardViewActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    protected void onResume() {
            super.onResume();
            ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                    .MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    Log.i(LOG_TAG, " Clicked on Item " + position);
                }
            });
    }

    private ArrayList<DataObject> getDataSet() {
            ArrayList results = new ArrayList<DataObject>();
            results.add(new DataObject("African", "cheh"));
            results.add(new DataObject("American", "cheh"));
            results.add(new DataObject("Arabian", "cheh"));
            results.add(new DataObject("Asian Fusion", "cheh"));
            results.add(new DataObject("Barbeque", "cheh"));
            results.add(new DataObject("Bistros", "cheh"));
            results.add(new DataObject("Breakfast & Brunch", "cheh"));
            results.add(new DataObject("Buffets", "cheh"));
            results.add(new DataObject("Burgers", "cheh"));
            results.add(new DataObject("Cafes", "cheh"));
            results.add(new DataObject("Cajun", "cheh"));
            results.add(new DataObject("Chicken Wings", "cheh"));
            results.add(new DataObject("Chinese", "cheh"));
            results.add(new DataObject("Comfort Food", "cheh"));
            results.add(new DataObject("Diners", "cheh"));
            results.add(new DataObject("Fast Food", "cheh"));
            results.add(new DataObject("Gastropubs", "cheh"));
            results.add(new DataObject("Gluten-Free", "cheh"));
            results.add(new DataObject("Greek", "cheh"));
            results.add(new DataObject("Halal", "cheh"));
            results.add(new DataObject("Indian", "cheh"));
            results.add(new DataObject("Italian", "cheh"));
            results.add(new DataObject("Japanese", "cheh"));
            results.add(new DataObject("Korean", "cheh"));
            results.add(new DataObject("Kosher", "cheh"));
            results.add(new DataObject("Mediterranean", "cheh"));
            results.add(new DataObject("Mexican", "cheh"));
            results.add(new DataObject("Middle Eastern", "cheh"));
            results.add(new DataObject("Pizza", "cheh"));
            results.add(new DataObject("Salad", "cheh"));
            results.add(new DataObject("Sandwiches", "cheh"));
            results.add(new DataObject("Seafood", "cheh"));
            results.add(new DataObject("Soul Food", "cheh"));
            results.add(new DataObject("Steakhouses", "cheh"));
            results.add(new DataObject("Thai", "cheh"));
            results.add(new DataObject("Vegan", "cheh"));
            results.add(new DataObject("Vegetarian", "cheh"));
            results.add(new DataObject("Vietnamese", "cheh"));

            return results;
    }

}
