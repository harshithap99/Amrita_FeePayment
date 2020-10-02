package com.example.amrita_placements.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.amrita_placements.R;

public class paypage extends AppCompatActivity {
    Button payment;
    EditText account_number,cvv,expiry_date,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.payment);
        payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_homepage();
            }
        });
    }
    public void go_to_homepage()
    {
        Intent intent = new Intent(this, verification.class);
        startActivity(intent);
    }
    public void findViews(){
        account_number = findViewById(R.id.account_number);
        name = findViewById(R.id.NAME);
        cvv = findViewById(R.id.Cvv);
        expiry_date = findViewById(R.id.Expiry_date);
    }
}