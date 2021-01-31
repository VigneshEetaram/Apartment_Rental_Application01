package com.example.apartment_rental_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ApartmentDetailDescription extends AppCompatActivity {

    private TextView Price,Description,Title;
    FloatingActionButton button;
    ImageButton share;
    ImageView Image,favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_detail_description);

        button = findViewById(R.id.message);
        Image = (ImageView) findViewById(R.id.Imagedes);
        Price= (TextView) findViewById(R.id.Pricedes);
        Description= (TextView) findViewById(R.id.Description);
        Title = (TextView) findViewById(R.id.titledes);
        favorite = findViewById(R.id.favorite);
        share = (ImageButton) findViewById(R.id.share);


    }
}