    package com.example.apartment_rental_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Renter_Home extends AppCompatActivity {

    ImageButton post,posted,viewapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter__home);
        post = (ImageButton) findViewById(R.id.postHomeButton);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Renter_Home.this,Post_Apartment_Page.class);
                startActivity(intent);

            }
        });

        posted = (ImageButton) findViewById(R.id.postedButton);
        posted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Renter_Home.this,MyPostedApartments.class);
                startActivity(intent);
            }
        });

        viewapt = findViewById(R.id.viewButton);
        viewapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Renter_Home.this,viewApartments.class);
                startActivity(intent);
            }
        });
    }
}