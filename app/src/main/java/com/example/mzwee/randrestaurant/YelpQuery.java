package com.example.mzwee.randrestaurant;

import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;

/**
 * Created by mzwee on 2/20/16.
 */
public class YelpQuery extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
    String consumerKey = "1jsFUVTrTBf7fWReBxt8Xg";
    String consumerSecret = "wNBLG0Sz05SOdWtWYOR5xGcTC1U";
    String token = "H4MaKPlMJvWZgsahXPPBKZcFza_-InqA";
    String tokenSecret = "TrXf9SUarkRcQqIrWKG0LCwn6q4";
    ListView listView;

    // Provides the entry point to Google Play Services
    protected GoogleApiClient mGoogleApiClient;

    // Represents a geographical location
    protected Location mLastLocation;

    // Set up Yelp API
    YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
    YelpAPI yelpAPI = apiFactory.createAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yelp_query);

        buildGoogleApiClient();

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

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

        CoordinateOptions coordinate = CoordinateOptions.builder().latitude(mLastLocation.getLatitude()).longitude(mLastLocation.getLongitude()).build();

        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
        try {
            SearchResponse searchResponse = call.execute().body();
            ArrayList<Business> businesses = searchResponse.businesses();
            for(Business b : businesses) {
                businessNames.add(b.name());
            }
            System.out.println(businessNames.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, businessNames);
        listView.setAdapter(adapter);


    }

    // Builds a GoogleApiClient. Uses the addApi() to request the LocationServices API
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    // Runs when a GoogleApiClient object successfully connects
    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation == null) {
            System.out.println("No location detected");
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }
}