package com.example.amrita_placements.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class formpage extends AppCompatActivity {
    Button submit;
    EditText student_name,student_reg_number,neft_name,bank_name,bank_place,mobile_number,amount,date,neft_id;
    String r_sname,r_amount,r_phonenumber;
    private FirebaseFirestore db;
    String feetype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.neftformpage);
        submit = findViewById(R.id.submit);
        findViews();
        retrieve_values();
        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");
        feetype = bundle.getString("type");
        assert user1 != null;
        String yearofuser = "20";
        for(int i =11; i<=12; i++)
        {
            yearofuser = yearofuser + user1.charAt(i);
        }
        Toast.makeText(getApplicationContext(), yearofuser, Toast.LENGTH_SHORT).show();




        final String finalYearofuser = yearofuser;
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
                else if(!studentreg.equals(user1)) {
                    student_reg_number.setError("Student Registration Number does not match");
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

                Map<String, Object> data = new HashMap<>();
                data.put("Student_name", studentname);
                data.put("Student_reg", studentreg);
                data.put("neft_name", neftname);
                data.put("neft_id", neftid);
                data.put("neft_amt", neftamount);
                data.put("neft_date", neftdate);
                data.put("bank_name", bankname);
                data.put("bankplace", bankplace);
                data.put("type", feetype);
                data.put("mob", mobilenumber);

                db.collection("FORMS").document(finalYearofuser).collection("collection")
                        .add(data);

                Bundle bundle = getIntent().getExtras();
                assert bundle != null;
                String user1 = bundle.getString("user1");

                Map<String, Object> flag_neft  = new HashMap<>();
                flag_neft.put(feetype+"neftflag", true);

                db.collection("students").document(user1)
                        .set(flag_neft, SetOptions.merge());

                go_to_processing(feetype);
            }
        });


        student_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    student_name.setText(r_sname);
            }
        });

        student_reg_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    student_reg_number.setText(user1);
            }
        });

        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    amount.setText(r_amount);
            }
        });

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear,mMonth,mDay;
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(formpage.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
            }
        }
                                      });

        mobile_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    mobile_number.setText(r_phonenumber);
            }
        });
    }
    public void go_to_processing(String type)
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, NeftProcessing.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        bundle1.putString("type", feetype);
        intent.putExtras(bundle1);
        startActivity(intent);
        this.finish();
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

    public void retrieve_values()
    {
        db = FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");
        assert user1 != null;

        DocumentReference reference = db.collection("students").document(user1);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                       r_sname = documentSnapshot.getString("name");
                       r_phonenumber = documentSnapshot.get("phonenumber").toString();
                       r_amount = documentSnapshot.get(feetype).toString();

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
