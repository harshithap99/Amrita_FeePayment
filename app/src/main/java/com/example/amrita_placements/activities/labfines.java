package com.example.amrita_placements.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class labfines extends AppCompatActivity {
    Button formid;
    Button payid;
    TextView AMT;
    int flag = 0;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lab_fines);
        db = FirebaseFirestore.getInstance();
        formid = findViewById(R.id.form_id);
        payid = findViewById(R.id.pay_id);
        AMT = findViewById(R.id.AMT);
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
                        amount = documentSnapshot.get("LABFEES").toString();
                        amount_int = (long) documentSnapshot.get("LABFEES");
                        if(amount_int>0)
                        {
                            flag=1;
                            AMT.setTextColor(Color.parseColor("#FFFFFF"));
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
                if(flag==1)
                    go_to_payment();
                else
                    Toast.makeText(getApplicationContext(), "No dues to be cleared", Toast.LENGTH_SHORT).show();
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
        bundle1.putString("type", "LABFEES");

        Intent intent = new Intent(this, formpage.class);
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
        bundle1.putString("whatfees", "LABFEES");
        bundle1.putString("amount", AMT.getText().toString());


        Intent intent = new Intent(this, paypage.class);
        intent.putExtras(bundle1);
        startActivity(intent);
        this.finish();
    }
}
