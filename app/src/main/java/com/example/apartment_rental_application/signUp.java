package com.example.apartment_rental_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity {

  EditText mName, mEmail, mPassword, mPhoneNumber;
  ImageView mImage;
  Button mRegisterBTN;
  Boolean valid = true;
  CheckBox mTenant, mRenter;

  FirebaseAuth fAuth;
  FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mName = findViewById(R.id.Register_name);
        mEmail = findViewById(R.id.Register_Email);
        mPassword = findViewById(R.id.Register_password);
        mPhoneNumber = findViewById(R.id.Register_phone);
        mImage = findViewById(R.id.Register_Image);
        mRegisterBTN = findViewById(R.id.Register_BTN);
        mRenter = findViewById(R.id.Register_renter);
        mTenant = findViewById(R.id.Register_tenant);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        /**
         * if tenant box is checked,this method check other one
         */

        mTenant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    mRenter.setChecked(false);
                }
            }
        });
        /**
         * if renter box is checked,this method check other one
         */

        mRenter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    mTenant.setChecked(false);
                }
            }
        });



        /**
         * Go Back to Login Activity
         */

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        /**
         * Create user and store data to database
         */

        mRegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checking null value
                checkField(mName);
                checkField(mEmail);
                checkField(mPassword);
                checkField(mPhoneNumber);

                //Checking check box is selected or not
                if (!(mRenter.isChecked() || mTenant.isChecked())){
                    Toast.makeText(signUp.this, "Please select one checkbox", Toast.LENGTH_SHORT).show();
                     return;
                }

                if(valid) {
                    fAuth.createUserWithEmailAndPassword(mEmail.getText().toString().trim(), mPassword.getText().toString().trim())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    Toast.makeText(signUp.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                                    DocumentReference documentReference = fStore.collection("users").document(user.getUid());
                                    Map<String, Object> userInfo = new HashMap<>();
                                    userInfo.put("FullName", mName.getText().toString().trim());
                                    userInfo.put("Email", mEmail.getText().toString().trim());
                                    userInfo.put("PhoneNumber", mPhoneNumber.getText().toString());


                                    if(mTenant.isChecked()){
                                        userInfo.put("isTenant", "1");
                                    }

                                    if(mRenter.isChecked()){
                                        userInfo.put("isRenter", "2");
                                    }
                                    documentReference.set(userInfo);


                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG","Register fail " + e.getMessage());
                            Toast.makeText(signUp.this, "register Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
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
}