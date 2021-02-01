package com.example.apartment_rental_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class MyPostedApartments extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_apartments);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        String Email = intent.getStringExtra("Email");

        Query query = firebaseFirestore.collection("Apartments").whereEqualTo("Email",Email);

        FirestoreRecyclerOptions<Model> options = new FirestoreRecyclerOptions.Builder<Model>()
                .setQuery(query, Model.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<Model, viewApartments.ItemViewHolder>(options) {
            @NonNull
            @Override
            public viewApartments.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycleview, parent, false);
                return new viewApartments.ItemViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull viewApartments.ItemViewHolder holder, int position, @NonNull Model model) {
                Picasso.get().load(model.getImage()).into(holder.Image);
                holder.Title.setText(model.getTitle());
                holder.Price.setText(model.getPrice());
                holder.Description.setText(model.getDescription());
                holder.More.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ApartmentDetailDescription.class);
                        intent.putExtra("Image", model.getImage());
                        intent.putExtra("Price", model.getPrice());
                        intent.putExtra("Title", model.getTitle());
                        intent.putExtra("Description", model.getDescription());
                        startActivity(intent);
                    }
                });

            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);

    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView Price, Title, Description;
        private ImageView Image;
        private Button More;
        String id;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
             Price = itemView.findViewById(R.id.Price_detail);
            Image = itemView.findViewById(R.id.Image_detail);
            More = itemView.findViewById(R.id.more);
            Title = itemView.findViewById(R.id.Title);
            Description = itemView.findViewById(R.id.description);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
