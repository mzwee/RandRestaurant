package com.example.mzwee.randrestaurant;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

/**
 * Created by User on 20-Feb-16.
 */
public class GridView extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        context = getApplicationContext();
        activity = this;

        // Create an instance of GoogleAPIClient
        if(mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        final Button American = (Button) findViewById(R.id.American);
        American.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("newamerican");
            }
        });

        final Button Chinese = (Button) findViewById(R.id.Chinese);
        Chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("chinese");
            }
        });

        final Button SouthEastAsia = (Button) findViewById(R.id.SouthEastAsia);
        SouthEastAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("malaysian");
            }
        });

        final Button Japanese = (Button) findViewById(R.id.Japanese);
        Japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("japanese");
            }
        });

        final Button Korean = (Button) findViewById(R.id.Korean);
        Korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("korean");
            }
        });

        final Button Arabian = (Button) findViewById(R.id.Arabian);
        Arabian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("arabian");
            }
        });

        final Button Italian = (Button) findViewById(R.id.Italian);
        Italian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("italian");
            }
        });

        final Button FastFood = (Button) findViewById(R.id.FastFood);
        FastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("hotdogs");
            }
        });

        final Button Indian = (Button) findViewById(R.id.Indian);
        Indian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("indpak");
            }
        });

        final Button Mediterranean = (Button) findViewById(R.id.Mediterranean);
        Mediterranean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("mediterranean");
            }
        });

        final Button French = (Button) findViewById(R.id.French);
        French.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("french");
            }
        });

        final Button Mexican = (Button) findViewById(R.id.Mexican);
        Mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                query("mexican");
            }
        });
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if(!checkPermission()) {
            requestPermission();
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if(result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void query(String category) {
        if(mLastLocation != null) {
            Intent intent = new Intent(GridView.this, YelpQuery.class);
            intent.putExtra("category", category);
            intent.putExtra("location", mLastLocation);
            startActivity(intent);
        }
    }
}
