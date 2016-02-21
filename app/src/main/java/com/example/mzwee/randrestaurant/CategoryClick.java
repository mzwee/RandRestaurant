package com.example.mzwee.randrestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 21-Feb-16.
 */
public class CategoryClick extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.grid_layout);

        final Button American = (Button) findViewById(R.id.American);
        American.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
            }
        });

        final Button Chinese = (Button) findViewById(R.id.Chinese);
        Chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
            }
        });

        final Button SouthEastAsia = (Button) findViewById(R.id.SouthEastAsia);
        SouthEastAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
            }
        });

        final Button Japanese = (Button) findViewById(R.id.Japanese);
        American.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
            }
        });

        final Button Korean = (Button) findViewById(R.id.Korean);
        Korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
            }
        });

        final Button Arabian = (Button) findViewById(R.id.Arabian);
        Arabian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
            }
        });

    }
}
