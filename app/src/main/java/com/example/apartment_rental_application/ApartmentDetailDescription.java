package com.example.apartment_rental_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

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


        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra("Image")).into(Image);
        String price = intent.getExtras().getString("Price");
        String title = intent.getExtras().getString("Title");
        String description = intent.getExtras().getString("Description");
        Price.setText(price);
        Title.setText(title);
        Description.setText(description);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),Messages.class);
                startActivity(i);
            }
        });


    }
}