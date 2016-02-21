package com.example.mzwee.randrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by User on 20-Feb-16.
 */
public class GridView extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        final Button American = (Button) findViewById(R.id.American);
        American.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "newamerican");
                startActivity(intent);
            }
        });

        final Button Chinese = (Button) findViewById(R.id.Chinese);
        Chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "chinese");
                startActivity(intent);
            }
        });

        final Button SouthEastAsia = (Button) findViewById(R.id.SouthEastAsia);
        SouthEastAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "malaysian");
                startActivity(intent);
            }
        });

        final Button Japanese = (Button) findViewById(R.id.Japanese);
        Japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "japanese");
                startActivity(intent);
            }
        });

        final Button Korean = (Button) findViewById(R.id.Korean);
        Korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "korean");
                startActivity(intent);
            }
        });

        final Button Arabian = (Button) findViewById(R.id.Arabian);
        Arabian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "arabian");
                startActivity(intent);
            }
        });

        final Button Italian = (Button) findViewById(R.id.Italian);
        Italian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "italian");
                startActivity(intent);
            }
        });

        final Button FastFood = (Button) findViewById(R.id.FastFood);
        FastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "hotdogs");
                startActivity(intent);
            }
        });

        final Button Indian = (Button) findViewById(R.id.Indian);
        Indian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "indpak");
                startActivity(intent);
            }
        });

        final Button Mediterranean = (Button) findViewById(R.id.Mediterranean);
        Mediterranean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "mediterranean");
                startActivity(intent);
            }
        });

        final Button French = (Button) findViewById(R.id.French);
        French.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "french");
                startActivity(intent);
            }
        });

        final Button Mexican = (Button) findViewById(R.id.Mexican);
        Mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform action on click
                Intent intent = new Intent(GridView.this, YelpQuery.class);
                intent.putExtra("category", "mexican");
                startActivity(intent);
            }
        });
    }

}
