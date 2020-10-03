package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class forgotpassword extends AppCompatActivity {
    EditText email,regnumber;
    Button submit;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    final FirebaseUser this_user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.forgot_password);
        submit = findViewById(R.id.submit);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        findViews();
        submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String user_email = email.getText().toString();
                        final String user_reg_number = regnumber.getText().toString();
                        if (TextUtils.isEmpty(user_reg_number)) {
                            regnumber.setError("Registration number is required");
                            return;
                        }
                        if (TextUtils.isEmpty(user_email)) {
                            email.setError("Email is required");
                            return;
                        }
                        DocumentReference reference = db.collection("students").document(user_reg_number);
                        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists())
                                {
                                    String got_email = "not got";
                                    got_email = documentSnapshot.getString("EMAIL");
                                    if(user_email.equals(got_email))
                                    {
                                        go_to_login();
                                    }
                                }
                                else
                                {
                                    regnumber.setError("Registration number is invalid");
                                }
                            }
                        });
                    }
                });
    }
    public void findViews(){
         email = findViewById(R.id.email);
         regnumber = findViewById(R.id.regnum);
    }
    public void go_to_login(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
