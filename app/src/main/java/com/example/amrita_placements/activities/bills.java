package com.example.amrita_placements.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class bills extends AppCompatActivity {


    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    TextView name,regno,mobile,date,paytype,accnumber,accname,pamount,feetype;
    String emailid;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.billpage);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.sname);
        regno = findViewById(R.id.regno);
        feetype = findViewById(R.id.feetype);
        date = findViewById(R.id.paydate);
        paytype = findViewById(R.id.paymod);
        accnumber = findViewById(R.id.batch);
        pamount = findViewById(R.id.paidamt);
        //feeamt = findViewById(R.id.feeamt);
        //paidamt = findViewById(R.id.paidamt);
        //paymod = findViewById(R.id.paymod);
        //paydate = findViewById(R.id.paydate);




        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String docname = bundle.getString("docname");
        String user1 = bundle.getString("user1");


        Toast.makeText(getApplicationContext(), user1, Toast.LENGTH_SHORT).show();
        DocumentReference reference = db.collection("students").document(user1).collection("reciepts").document(docname);


        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {


                        name.setText(documentSnapshot.getString("Student_name"));
                        regno.setText(documentSnapshot.getString("Student_reg"));
                        mobile.setText(documentSnapshot.getString("mobile"));


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
}