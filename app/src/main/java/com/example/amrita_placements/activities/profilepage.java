package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class profilepage extends AppCompatActivity {
    Button Change_password;
    Button View_receipts;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    final FirebaseUser this_user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile_page);
        Change_password = findViewById(R.id.button);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        View_receipts = findViewById(R.id.view_receipts);
        Change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_change_password();
            }
        });
        View_receipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_view_receipts();
            }
        });
    }
    public void go_to_change_password()
    {
        Intent intent = new Intent(this, changepassword.class);
        startActivity(intent);
    }
    public void go_to_view_receipts()
    {
        Intent intent = new Intent(this, Viewreceipts.class);
        startActivity(intent);
    }
}
