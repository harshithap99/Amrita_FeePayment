package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.amrita_placements.R;

public class verify_reg_number extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.homepage1);
        CardView hostel_fees = findViewById(R.id.hostelfees);
        CardView bus_fees = findViewById(R.id.busfees);
        CardView tuition_fees = findViewById(R.id.tutionfees);
        CardView mess_fees =  findViewById(R.id.messfees);
        CardView library_fines = findViewById(R.id.libraryfines);
        CardView lab_fines = findViewById(R.id.labfines);
        CardView canteen_dues = findViewById(R.id.canteendues);
        CardView other_fees = findViewById(R.id.generalfines);

        hostel_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             A();
            }
        });
        bus_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tuition_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mess_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        library_fines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        canteen_dues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lab_fines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        other_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    public void jj()
    {

        Intent intent = new Intent(this, paypage.class);
        startActivity(intent);
    }
    public void A()
    {
        Intent intent = new Intent(this, hostelfees.class);
        startActivity(intent);
    }
}
