package com.example.apartment_rental_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ApartmentDetailDescription extends AppCompatActivity {

    private TextView Price,Description,Title;
    FloatingActionButton button;
    ImageButton share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_detail_description);


    }
}