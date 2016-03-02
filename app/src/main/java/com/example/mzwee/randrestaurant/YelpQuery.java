package com.example.mzwee.randrestaurant;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Debug;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
/**/

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
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
import java.util.HashMap;
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

    // Set up Yelp API
    YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
    YelpAPI yelpAPI = apiFactory.createAPI();

    private String category;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.business_list);
        Intent i = getIntent();
        category = getIntent().getExtras().getString("category");
        location = getIntent().getExtras().getParcelable("location");

        // Activate StrictMode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Map<String, String> params = new HashMap<>();

        // params
        params.put("lang", "en");
        params.put("cc", "US");
        params.put("term", "restaurants");
        params.put("category_filter", category);
        params.put("radius_filter", "3000");

        CoordinateOptions coordinate = CoordinateOptions.builder().latitude(location.getLatitude()).longitude(location.getLongitude()).build();

        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
        try {
            SearchResponse searchResponse = call.execute().body();
            if (searchResponse.businesses().size() == 0) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
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

}