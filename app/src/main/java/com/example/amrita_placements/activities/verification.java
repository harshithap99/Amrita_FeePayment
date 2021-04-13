package com.example.amrita_placements.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class verification extends AppCompatActivity {
    private FirebaseFirestore db;
    StorageReference gsReference;
    String r_sname,r_phonenumber,r_email,picloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
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
        final Intent intent = new Intent(this, profilepage.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);

        DocumentReference reference = db.collection("students").document(user1);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    r_sname = documentSnapshot.getString("name");
                    r_phonenumber = documentSnapshot.get("phonenumber").toString();
                    r_email = documentSnapshot.get("email").toString();
                    picloc = documentSnapshot.get("picloc").toString();
                    bundle1.putString("phone", r_phonenumber);
                    bundle1.putString("name", r_sname);
                    bundle1.putString("email", r_email);
                    bundle1.putString("picloc", picloc);

                    intent.putExtras(bundle1);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                        flagforneft = (boolean) documentSnapshot.get("HOSTELFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("HOSTELFEES");
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
                        flagforneft = (boolean) documentSnapshot.get("MESSFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("MESSFEES");
                        }
                        else
                        {

                            Intent intent = new Intent(verification.this, messfees.class);
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


    public void go_to_tutionfees()
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
                        flagforneft = (boolean) documentSnapshot.get("TUTIONFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("TUTIONFEES");
                        }
                        else
                        {
                            Intent intent = new Intent(verification.this, tuitionfees.class);
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
    public void go_to_busfees()
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
                        flagforneft = (boolean) documentSnapshot.get("BUSFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("BUSFEES");
                        }
                        else
                        {
                            Intent intent = new Intent(verification.this, busfees.class);
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
    public void go_to_canteendues()
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
                        flagforneft = (boolean) documentSnapshot.get("CANTEENFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("CANTEENFEES");
                        }
                        else
                        {
                            Intent intent = new Intent(verification.this, canteendues.class);
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


    public void go_to_labfines()
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
                        flagforneft = (boolean) documentSnapshot.get("LABFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("LABFEES");
                        }
                        else
                        {
                            Intent intent = new Intent(verification.this, labfines.class);
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

    public void go_to_libraryfines()
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
                        flagforneft = (boolean) documentSnapshot.get("LIBFEESneftflag");
                        if(flagforneft)
                        {
                            go_to_processing("LIBFEES");
                        }
                        else
                        {

                            Intent intent = new Intent(verification.this, libraryfines.class);
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


    public void go_to_otherfees()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, courses.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        intent.putExtras(bundle1);
        startActivity(intent);
    }

    public void go_to_processing(String type)
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, NeftProcessing.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        bundle1.putString("type", type);
        intent.putExtras(bundle1);
        startActivity(intent);

    }
}
