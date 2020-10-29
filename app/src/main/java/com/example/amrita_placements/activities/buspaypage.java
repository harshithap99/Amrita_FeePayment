package com.example.amrita_placements.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class buspaypage extends AppCompatActivity {
    Button payment;
    EditText account_number,cvv,expiry_date,name;
    String accountnumber,accountname,accountcvv,accountexpiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.payment);
        findViews();
        payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountnumber = account_number.getText().toString();
                accountname = name.getText().toString();
                accountcvv = cvv.getText().toString();
                accountexpiry = expiry_date.getText().toString();
                if (TextUtils.isEmpty(accountnumber)) {
                    account_number.setError("Account number is required");
                    return;
                }
                if (TextUtils.isEmpty(accountname)) {
                    name.setError("Account holder name is required");
                    return;
                }
                if (TextUtils.isEmpty(accountcvv)) {
                    cvv.setError("CVV is required");
                    return;
                }
                if (TextUtils.isEmpty(accountexpiry)) {
                    expiry_date.setError("Expiry date is required");
                    return;
                }
                // we need to check this with the database and then detect the amount from user collection
                changelayout();
            }
        });
    }
    public void changelayout()
    {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                        String sname,phonenumber,amount,email;
                        Date currentTime = Calendar.getInstance().getTime();
                        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                        sname = documentSnapshot.getString("name");
                        phonenumber = documentSnapshot.get("phonenumber").toString();
                        amount = documentSnapshot.get("BUS FEES").toString();
                        email = documentSnapshot.getString("email");
                        Map<String, Object> data = new HashMap<>();
                        data.put("Student_name", sname);
                        data.put("Student_reg", user1);
                        data.put("acc_name", accountname);
                        data.put("acc_number", accountnumber);
                        data.put("amt", amount);
                        data.put("mobile", phonenumber);
                        data.put("emailid", email);
                        data.put("paytype", "mobile payment");
                        data.put("date", currentTime.toString());

                        Map<String, Object> data1 = new HashMap<>();
                        data1.put("BUS FEES", 0);
                        db.collection("students").document(user1).collection("reciepts").document("busfee"+currentDate)
                                .set(data);

                        db.collection("students").document(user1)
                                .set(data1, SetOptions.merge());

                    } else {
                        Toast.makeText(getApplicationContext(), "didnt find the doc in firebase", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });



        setContentView(R.layout.paymentsuccess);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(buspaypage.this, verification.class);
                final Bundle bundle1 = new Bundle();
                bundle1.putString("user1", user1);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }
    public void findViews()
    {
        account_number = findViewById(R.id.account_number);
        name = findViewById(R.id.NAME);
        cvv = findViewById(R.id.Cvv);
        expiry_date = findViewById(R.id.Expiry_date);
    }
}