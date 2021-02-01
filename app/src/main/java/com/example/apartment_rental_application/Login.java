package com.example.apartment_rental_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText lEmail, lPassword;
    FirebaseFirestore lStore;
    FirebaseAuth lAuth;
    Button lLogin,lRegister, lForgotPassword, lAdminLogin;
    Boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lEmail = findViewById(R.id.Login_Email);
        lPassword = findViewById(R.id.Login_Password);
        lLogin = findViewById(R.id.LogIn_BTN);
        lRegister = findViewById(R.id.Login_register);
        lForgotPassword = findViewById(R.id.Register_forgot);
        lAdminLogin = findViewById(R.id.btn_adminLogin);

        lAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),adminLogin.class));
            }
        });


        lStore = FirebaseFirestore.getInstance();
        lAuth = FirebaseAuth.getInstance();



        /**
         * take back to register page
         */

        lRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),signUp.class));
                finish();;
            }
        });

        /**
         * performing login activity
         */

        if(valid){

            lLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkField(lEmail);
                    checkField(lPassword);
                    lAuth.signInWithEmailAndPassword(lEmail.getText().toString(),lPassword.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    checkAccessLevel(authResult.getUser().getUid());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG","Error Login "+ e.getMessage());
                            Toast.makeText(Login.this, "fail login", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        }



    }

    /**
     * Checking access levels for users
     * @param uid String
     */

    private void checkAccessLevel(String uid) {
        DocumentReference documentReference = lStore.collection("users").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess" + documentSnapshot.getData());
                if (documentSnapshot.getString("isAdmin") != null){
                    startActivity(new Intent(getApplicationContext(),Admin.class));
                    finish();
                }
                if (documentSnapshot.getString("isTenant") != null){
                    startActivity(new Intent(getApplicationContext(),Tenant.class));
                    finish();
                }
                if(documentSnapshot.getString("isRenter") != null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });
    }


    /**
     * Checking text field is not empty
     * @param TextField
     * @return Boolean
     */

    private Boolean checkField(EditText TextField) {

        if(TextField.getText().toString().isEmpty()){
            TextField.setError("Error");
            valid = false;
        }else{
            valid = true;
        }
        return valid;

    }

    /**
     * Checking user previously logged-n or not
     */
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    if (documentSnapshot.getString("isAdmin") != null){
                        startActivity(new Intent(getApplicationContext(),Admin.class));
                        finish();
                    }
                    if (documentSnapshot.getString("isTenant") != null){
                        startActivity(new Intent(getApplicationContext(),Tenant.class));
                        finish();
                    }

                    if (documentSnapshot.getString("isRenter") != null){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
            });

        }
    }
}