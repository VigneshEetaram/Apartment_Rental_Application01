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

public class viewApartments extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_apartments);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        Query query = firebaseFirestore.collection("Apartments");

        FirestoreRecyclerOptions<Model> options = new FirestoreRecyclerOptions.Builder<Model>()
                .setQuery(query,Model.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<Model, ItemViewHolder>(options) {
            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycleview,parent,false);
                return new ItemViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull Model model) {
                Picasso.get().load(model.getImage()).into(holder.Image);
                holder.Title.setText(model.getTitle());
                holder.Price.setText(model.getPrice());
                holder.Description.setText(model.getDescription());
                holder.Email.setText(model.getDescription());
                holder.More.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(),ApartmentDetailDescription.class);
                        intent.putExtra("Image",model.getImage());
                        intent.putExtra("Price",model.getPrice());
                        intent.putExtra("Title",model.getTitle());
                        intent.putExtra("Description",model.getDescription());
                        intent.putExtra("Email",model.getDescription());
                        startActivity(intent);
                    }
                });

            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter);

    }
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView Price;
        TextView Email;
        TextView Title;
        TextView Description;
        ImageView Image;
        Button More;
        String id;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            Price = itemView.findViewById(R.id.Price_detail);
            Email = itemView.findViewById(R.id.Email);
            Image = itemView.findViewById(R.id.Image_detail);
            More = itemView.findViewById(R.id.more);
            Title = itemView.findViewById(R.id.Title);
            Description=itemView.findViewById(R.id.description);
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