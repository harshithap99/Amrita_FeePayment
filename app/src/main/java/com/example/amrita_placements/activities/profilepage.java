package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;

public class profilepage extends AppCompatActivity {
    Button Change_password;
    Button View_receipts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile_page);
        Change_password = findViewById(R.id.button);
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
