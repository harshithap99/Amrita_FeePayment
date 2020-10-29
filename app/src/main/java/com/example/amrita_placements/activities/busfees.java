package com.example.amrita_placements.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class busfees extends AppCompatActivity {
    Button formid;
    Button payid;
    TextView AMT;
    int flag=0;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.busfees);
        formid = findViewById(R.id.form_id);
        payid = findViewById(R.id.pay_id);
        db = FirebaseFirestore.getInstance();
        AMT = findViewById(R.id.displayamount);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");
        DocumentReference reference = db.collection("students").document(user1);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        String amount = "not got";
                        long amount_int;
                        amount = documentSnapshot.get("BUS FEES").toString();
                        amount_int = (long) documentSnapshot.get("BUS FEES");
                        if(amount_int>0)
                        {
                            flag=1;
                        }
                        AMT.setText(amount);

                    } else {
                        Toast.makeText(getApplicationContext(), "didnt find the doc in firebase", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        formid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==1)
                    go_to_formpage();
                else
                    Toast.makeText(getApplicationContext(), "No dues to be cleared", Toast.LENGTH_SHORT).show();
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
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);


        Intent intent = new Intent(this, busformpage.class);
        intent.putExtras(bundle1);
        startActivity(intent);
        this.finish();
    }
    public void go_to_neftpage()
    {
        Intent intent = new Intent(this, neftpage.class);
        startActivity(intent);
    }
    public void go_to_payment()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);


        Intent intent = new Intent(this, buspaypage.class);
        intent.putExtras(bundle1);
        startActivity(intent);
        this.finish();
    }
}
