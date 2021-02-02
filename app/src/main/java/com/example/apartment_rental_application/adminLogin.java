package com.example.apartment_rental_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class adminLogin extends AppCompatActivity {
    EditText admEmail,admPass;
    Button adminLogin;
    Button Logout;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admEmail = (EditText)findViewById(R.id.txtAdminEmail);
        admPass = (EditText)findViewById(R.id.txtAdminPassword);
        adminLogin = (Button)findViewById(R.id.btnAdminLogin);
        Logout = (Button)findViewById(R.id.logoutadmin);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConditions();
            }
        });



    }

    private void checkConditions() {
        email = admEmail.getText().toString();
        password = admPass.getText().toString();

        if(email.isEmpty()){
            admEmail.setError("Email is required");
            admEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            admEmail.setError("Provide valid email");
            admEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            admPass.setError("Password is required");
            admPass.requestFocus();
            return;
        }
        checkLogin();
    }



    private void checkLogin() {
        if(email.equals("admin@gmail.com") &&
                password.equals("admin")){
            startActivity(new Intent(adminLogin.this,adminHome.class));
        }
        else{
            Toast.makeText(adminLogin.this, "Failed to login! Try again.", Toast.LENGTH_SHORT).show();

        }
    }
}