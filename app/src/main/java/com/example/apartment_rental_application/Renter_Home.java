    package com.example.apartment_rental_application;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageButton;

    import androidx.appcompat.app.AppCompatActivity;

    import com.google.firebase.auth.FirebaseAuth;

    public class Renter_Home extends AppCompatActivity {

    ImageButton post,posted,viewapt;
    Button logout;

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
                Intent intent = getIntent();
                String Email = intent.getStringExtra("Email");
                Intent i = new Intent(getApplicationContext(),MyPostedApartments.class);
                intent.putExtra("Email",Email);
                startActivity(i);
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

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

    }
}