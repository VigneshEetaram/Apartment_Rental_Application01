package com.example.apartment_rental_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Renter_Home extends AppCompatActivity {

    ImageButton post;

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
    }
}