package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class libraryfines extends AppCompatActivity {
    Button formid;
    Button payid;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    final FirebaseUser this_user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.library_dues);
        formid = findViewById(R.id.form_id);
        payid = findViewById(R.id.pay_id);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        formid.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference reference = db.collection("students").document(this_user.getUid());
                reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            String got_flag = "not got";
                            got_flag = documentSnapshot.getString("BUS_FLAG");
                            if (got_flag.equals("False"))
                            {
                                go_to_formpage();
                            }
                            else {
                                go_to_neftpage();
                            }
                        }
                    }
                });
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
    public void go_to_neftpage()
    {
        Intent intent = new Intent(this, neftpage.class);
        startActivity(intent);
    }
    public void go_to_payment()
    {
        Intent intent = new Intent(this, paypage.class);
        startActivity(intent);
    }
}
