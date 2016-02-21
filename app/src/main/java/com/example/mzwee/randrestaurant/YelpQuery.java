package com.example.mzwee.randrestaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**/

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit.Call;

/**
 * Created by mzwee on 2/20/16.
 */
public class YelpQuery extends AppCompatActivity {
    //    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
    String consumerKey = "1jsFUVTrTBf7fWReBxt8Xg";
    String consumerSecret = "wNBLG0Sz05SOdWtWYOR5xGcTC1U";
    String token = "H4MaKPlMJvWZgsahXPPBKZcFza_-InqA";
    String tokenSecret = "TrXf9SUarkRcQqIrWKG0LCwn6q4";
    ImageView img;
//    ListView listView;

    // Provides the entry point to Google Play Services
//    protected GoogleApiClient mGoogleApiClient;

    // Represents a geographical location
    protected Location mLastLocation;

    // Set up Yelp API
    YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
    YelpAPI yelpAPI = apiFactory.createAPI();

//    private GoogleApiClient client;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_list);
        Intent i = getIntent();
//        category = i.getStringArrayListExtra("category");
//        category = getIntent().getExtras().getStringArrayList("category");
        category = getIntent().getExtras().getString("category");
//        System.out.println(category.size());

//        buildGoogleApiClient();

        // Activate StrictMode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Get ListView object from xml
//        listView = (ListView) findViewById(R.id.list);

        Map<String, String> params = new HashMap<>();

        // params
        params.put("lang", "en");
        params.put("cc", "US");
        params.put("term", "restaurants");
        params.put("category_filter", category);
        params.put("radius_filter", "3000");

//        final List<String> businessNames = new ArrayList<String>();

        CoordinateOptions coordinate = CoordinateOptions.builder().latitude(40.1).longitude(-88.23).build();
//        if(mLastLocation != null) {
//            coordinate = CoordinateOptions.builder().latitude(mLastLocation.getLatitude()).longitude(mLastLocation.getLongitude()).build();
//        } else {
//            coordinate = CoordinateOptions.builder().latitude(40.109387).longitude(-88.2272456).build();
//        }
        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
        try {
            SearchResponse searchResponse = call.execute().body();
            if(searchResponse.businesses().size() == 0) {
                Toast.makeText(this, "Sorry, no results found.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(YelpQuery.this, GridView.class);
                startActivity(intent);
                return;
            }
            Random r = new Random();
            int value = r.nextInt(searchResponse.businesses().size());
            Business selected = searchResponse.businesses().get(value);
            TextView t = (TextView) findViewById(R.id.name);
            t.setText(selected.name());
            t = (TextView) findViewById(R.id.contact);
            t.setText("Contact Number: " + selected.phone());
            t = (TextView) findViewById(R.id.review);
            t.setText("# Reviews: " + selected.reviewCount());
            t = (TextView) findViewById(R.id.rating);
            t.setText("Rating: " + selected.rating());
            t = (TextView) findViewById(R.id.address);
            t.setText(selected.location().displayAddress().toString());
            img = (ImageView) findViewById(R.id.imageView);

            GetXMLTask task = new GetXMLTask();
            task.execute(new String[]{selected.imageUrl()});
//            businessNames.add(selected.name());
//            businessNames.add(selected.phone());
//            businessNames.add(Double.toString(selected.reviewCount()));
//            businessNames.add(Double.toString(selected.rating()));
//            businessNames.add(selected.location().displayAddress().toString());
//            businessNames.add(selected.imageUrl());

//            ArrayList<Business> businesses = searchResponse.businesses();
//            for (Business b : businesses) {
//                businessNames.add(b.name());
//            }
//            System.out.println(businessNames.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, businessNames);
//        listView.setAdapter(adapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "YelpQuery Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.mzwee.randrestaurant/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "YelpQuery Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.mzwee.randrestaurant/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }

    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for(String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }


    // Builds a GoogleApiClient. Uses the addApi() to request the LocationServices API
//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "YelpQuery Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.mzwee.randrestaurant/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "YelpQuery Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.mzwee.randrestaurant/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }
//
//    // Runs when a GoogleApiClient object successfully connects
//    @Override
//    public void onConnected(Bundle connectionHint) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
////            askForPermission();
//            return;
//        }
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if(mLastLocation == null) {
//            System.out.println("No location detected");
//        }
//    }
//
//    private void askForPermission() {
//        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
//        ActivityCompat.requestPermissions(
//                this,
//                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
//                PERMISSION_LOCATION_REQUEST_CODE);
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        mGoogleApiClient.connect();
//    }
}