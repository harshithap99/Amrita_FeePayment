package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class verification extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.homepage1);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");

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
        this.finish();
    }
    public void go_to_profile()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, profilepage.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        intent.putExtras(bundle1);
        startActivity(intent);
    }
    public void go_to_hostelfees()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");
        db = FirebaseFirestore.getInstance();


        assert user1 != null;
        Toast.makeText(getApplicationContext(), user1, Toast.LENGTH_SHORT).show();
        DocumentReference reference = db.collection("students").document(user1);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        boolean flagforneft = false;
                        flagforneft = (boolean) documentSnapshot.get("neftflag");
                        if(flagforneft)
                        {
                            go_to_processing();
                        }
                        else
                        {
                            Intent intent = new Intent(verification.this, hostelfees.class);
                            final Bundle bundle1 = new Bundle();
                            bundle1.putString("user1", user1);
                            intent.putExtras(bundle1);
                            startActivity(intent);

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "didnt find the doc in firebase", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });




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

    public void go_to_processing()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, NeftProcessing.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        intent.putExtras(bundle1);
        startActivity(intent);

    }
}
