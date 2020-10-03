package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class formpage extends AppCompatActivity {
    Button submit;
    EditText student_name,student_reg_number,neft_name,bank_name,bank_place,mobile_number,amount,date,neft_id;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    final FirebaseUser this_user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.neftformpage);
        submit = findViewById(R.id.submit);
        findViews();
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentname = student_name.getText().toString();
                String studentreg= student_reg_number.getText().toString();
                String neftname= neft_name.getText().toString();
                String neftid= neft_id.getText().toString();
                String neftamount = amount.getText().toString();
                String bankname= bank_name.getText().toString();
                String bankplace = bank_place.getText().toString();
                String mobilenumber= mobile_number.getText().toString();
                String neftdate = date.getText().toString();
                if (TextUtils.isEmpty(studentname)) {
                    student_name.setError(" Student Name is required");
                    return;
                }
                if (TextUtils.isEmpty(studentreg)) {
                    student_reg_number.setError("Student Registration Number is required");
                    return;
                }
                if (TextUtils.isEmpty(neftname)) {
                    neft_name.setError(" Person Name is required is required");
                    return;
                }
                if (TextUtils.isEmpty(neftid)) {
                    neft_id.setError("Neft ID is required");
                    return;
                }
                if (TextUtils.isEmpty(neftamount)) {
                   amount .setError("Neft Amount is required");
                    return;
                }
                if (TextUtils.isEmpty(bankname)) {
                    bank_name.setError("BankName is required");
                    return;
                } if (TextUtils.isEmpty(bankplace)) {
                    bank_place.setError("Bank Branch is required");
                    return;
                }
                if (TextUtils.isEmpty(mobilenumber)) {
                    mobile_number.setError("Mobile Number is required");
                    return;
                }
                if (TextUtils.isEmpty(neftdate)) {
                    date.setError("Neft Date is required");
                    return;
                }

                final DocumentReference reference = db.collection("students").document(this_user.getUid());
                reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            // GEORGE CHECK HERE
                            String get_branch = "not got";
                            final String TAG = "";
                            get_branch = documentSnapshot.getString("BATCH");
                            Map<String, Object> data1 = new HashMap<>();
                            data1.put("name", "San Francisco");
                            data1.put("state", "CA");
                            data1.put("country", "USA");
                            data1.put("capital", false);
                            data1.put("population", 860000);
                            data1.put("regions", Arrays.asList("west_coast", "norcal"));
                            db.collection("FORM").document("2017").set(data1)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });
                           // we need to store all the data in the form collection with the user id
                        }
                    }
                });
            }
        });
    }
    public void go_to_homepage()
    {
        Intent intent = new Intent(this, verification.class);
        startActivity(intent);
    }
    public void findViews()
    {
        student_name = findViewById(R.id.Sname);
        student_reg_number = findViewById(R.id.Sreg);
        neft_name = findViewById(R.id.Pname);
        neft_id = findViewById(R.id.NEFTid);
        amount = findViewById(R.id.NEFTamt);
        date = findViewById(R.id.NEFTdate);
        bank_name = findViewById(R.id.bankName);
        bank_place = findViewById(R.id.BankPlace);
        mobile_number = findViewById(R.id.Snumber);
    }
}
