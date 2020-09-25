package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;

public class busfees extends AppCompatActivity {
    Button formid;
    Button payid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.busfees);
        formid = findViewById(R.id.form_id);
        payid = findViewById(R.id.pay_id);
        formid.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_formpage();
            }
        });
        payid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_payment();
            }
        });
    }
    public void go_to_formpage()
    {
        Intent intent = new Intent(this, formpage.class);
        startActivity(intent);
    }
    public void go_to_payment()
    {
        Intent intent = new Intent(this, paypage.class);
        startActivity(intent);
    }
}
