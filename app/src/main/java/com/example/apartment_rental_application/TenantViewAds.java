package com.example.apartment_rental_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TenantViewAds extends AppCompatActivity {
    private DatabaseReference AdRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_view_ads);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AdRef = FirebaseDatabase.getInstance().getReference().child("Ads");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<AdsRenter> options = new FirebaseRecyclerOptions.Builder<AdsRenter>().setQuery(AdRef,AdsRenter.class).build();

        FirebaseRecyclerAdapter<AdsRenter, AdAdapter> adapter = new FirebaseRecyclerAdapter<AdsRenter, AdAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdAdapter holder, int position, @NonNull AdsRenter model) {
                holder.prdName.setText(model.getPname());
                holder.prdPrice.setText(model.getPrice());
                Picasso.get().load(model.getImage()).into(holder.imgProduct);
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TenantViewAds.this, TenantAdDetails.class);
                        intent.putExtra("pid",model.getPid());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public AdAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aditemlayout, parent,false);
                AdAdapter holder = new AdAdapter(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}