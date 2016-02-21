package com.example.mzwee.randrestaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by mzwee on 2/20/16.
 */
public class YelpQuery extends AppCompatActivity {
    String consumerKey = "1jsFUVTrTBf7fWReBxt8Xg";
    String consumerSecret = "wNBLG0Sz05SOdWtWYOR5xGcTC1U";
    String token = "H4MaKPlMJvWZgsahXPPBKZcFza_-InqA";
    String tokenSecret = "TrXf9SUarkRcQqIrWKG0LCwn6q4";
    ListView listView;

    // Set up Yelp API
    YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
    YelpAPI yelpAPI = apiFactory.createAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yelp_query);

        // Activate StrictMode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Get ListView object from xml
        listView = (ListView)findViewById(R.id.list);

        Map<String, String> params = new HashMap<>();

        // params
        params.put("lang", "en");
        params.put("cc", "US");

        final List<String> businessNames = new ArrayList<String>();

        Call<SearchResponse> call = yelpAPI.search("Ann Arbor", params);
        try {
            SearchResponse searchResponse = call.execute().body();
            ArrayList<Business> businesses = searchResponse.businesses();
            for(Business b : businesses) {
                businessNames.add(b.name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, businessNames);
        listView.setAdapter(adapter);
    }
}
