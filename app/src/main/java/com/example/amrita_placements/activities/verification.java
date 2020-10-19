package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.amrita_placements.R;

public class verification extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.homepage1);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Toast.makeText(getApplicationContext(), user1, Toast.LENGTH_SHORT).show();

        ImageButton log_out, profile;
        CardView hostelfees, busfees, canteendues, libraryfines, otherfees, tutionfees, messfees, labfines;

        hostelfees = findViewById(R.id.hostelfees);
        busfees = findViewById(R.id.busfees);
        canteendues = findViewById(R.id.canteendues);
        libraryfines = findViewById(R.id.libraryfines);
        otherfees = findViewById(R.id.generalfines);
        tutionfees = findViewById(R.id.tutionfees);
        messfees = findViewById(R.id.messfees);
        labfines = findViewById(R.id.labfines);
        log_out = findViewById(R.id.logout);
        profile = findViewById(R.id.profile);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_login();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_profile();
            }
        });
        hostelfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_hostelfees();
            }
        });
        busfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_busfees();
            }
        });
        tutionfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_tutionfees();
            }
        });
        messfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_messfees();
            }
        });
        libraryfines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_libraryfines();
            }
        });
        canteendues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_canteendues();
            }
        });
        labfines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_labfines();
            }
        });
        otherfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_otherfees();
            }
        });
    }
    public void go_to_login()
    {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void go_to_profile()
    {
        Intent intent = new Intent(this, profilepage.class);
        startActivity(intent);
    }
    public void go_to_hostelfees()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);

        Intent intent = new Intent(this, hostelfees.class);
        intent.putExtras(bundle1);
        startActivity(intent);
    }
    public void go_to_messfees()
    {
        Intent intent = new Intent(this, messfees.class);
        startActivity(intent);
    }
    public void go_to_tutionfees()
    {
        Intent intent = new Intent(this, tuitionfees.class);
        startActivity(intent);
    }
    public void go_to_busfees()
    {
        Intent intent = new Intent(this, busfees.class);
        startActivity(intent);
    }
    public void go_to_canteendues()
    {
        Intent intent = new Intent(this, canteendues.class);
        startActivity(intent);
    }
    public void go_to_labfines()
    {
        Intent intent = new Intent(this, labfines.class);
        startActivity(intent);
    } public void go_to_libraryfines()
    {
        Intent intent = new Intent(this, libraryfines.class);
        startActivity(intent);
    }
    public void go_to_otherfees()
    {
        Intent intent = new Intent(this, otherdues.class);
        startActivity(intent);
    }
}
