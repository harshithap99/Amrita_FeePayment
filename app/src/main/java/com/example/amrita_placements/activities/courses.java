package com.example.amrita_placements.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class courses extends AppCompatActivity {

    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  ViewPageAdapter adapter;
    private List<assigner> listcourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_courses);
        db = FirebaseFirestore.getInstance();

        listcourses = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");


        db.collection("WORKSHOPS").document("CSE").collection("workshops")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("BROOO", document.getId() + " => " + document.getData() + document.getId());


                                listcourses.add(new assigner(document.getId().toString(), document.get("short").toString()));


                            }


                            listcourses.add(new assigner("AMAZON","Amazon workshop"));



                            tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
                            viewPager = (ViewPager) findViewById(R.id.viewpaper_id);
                            adapter = new ViewPageAdapter(getSupportFragmentManager());

                            adapter.AddFragment(new FragmentCall(user1,listcourses),"Courses");
                            adapter.AddFragment(new FragmentReg(),"Registered");

                            viewPager.setAdapter(adapter);
                            tabLayout.setupWithViewPager(viewPager);

                        } else {
                            Log.d("BROOO", "Error getting documents: ", task.getException());
                        }
                    }
                });




        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setElevation(0);

    }
}