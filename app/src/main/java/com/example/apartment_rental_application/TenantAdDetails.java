package com.example.apartment_rental_application;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TenantAdDetails extends AppCompatActivity {
    ImageView imgProductDetails;
    FirebaseUser user;
    DatabaseReference reference;
    String fullName;
    String userID;
    private String ImageID;
    TextView txtProductName,txtProductPrice,txtProductDescription;
    String productId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_ad_details);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);
                if (profile != null) {
                    fullName = profile.name;
                    String email = profile.email;
                    String age = profile.age;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TenantAdDetails.this, "Something went wrong. Restart the app.", Toast.LENGTH_SHORT).show();

            }
        });

        productId = getIntent().getStringExtra("pid");
        imgProductDetails = (ImageView) findViewById(R.id.imgProductDetails);
        txtProductName = (TextView) findViewById(R.id.txtProductName);
        txtProductPrice = (TextView) findViewById(R.id.txtProductPrice);
        txtProductDescription = (TextView) findViewById(R.id.txtProductDescription);

        getAdDetails(productId);

    }

    private void getAdDetails(String productId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products");
        reference.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    AdsRenter ad = snapshot.getValue(AdsRenter.class);
                    if(ad!=null) {

                        txtProductName.setText(ad.getPname());
                        txtProductPrice.setText(ad.getPrice());
                        txtProductDescription.setText(ad.getDescription());
                        Picasso.get().load(ad.getImage()).into(imgProductDetails);
                        ImageID=ad.getImage();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}