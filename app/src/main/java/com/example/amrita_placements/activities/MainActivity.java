package com.example.amrita_placements.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrita_placements.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button loginb;
    EditText reg_num;
    EditText password;
    FirebaseAuth fAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_test);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        findViews();
        loginb = (Button) findViewById(R.id.login_id);
        loginb.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                A();
                // we need to remove the function call from here and call it after conforming the credentials
                final String user_reg_number = reg_num.getText().toString();
                final String pass_word = password.getText().toString();
                if (TextUtils.isEmpty(user_reg_number)) {
                    reg_num.setError("Registration number.is.required");
                    return;
                }
                if (TextUtils.isEmpty(pass_word)) {
                    password.setError("password.is.empty");
                    return;
                }
               // if(Objects.equals(user_reg_number, "BL.EN.U4CSE17044")&&Objects.equals(pass_word,"BL.EN.U4CSE17044"))
                //{
                  // A();
                //}else
                //{
                  //  Toast.makeText(MainActivity.this,"Incorrect UserName or Password",Toast.LENGTH_LONG).show();
                //}
                //A();
               //DocumentReference documentref = FirebaseFirestore.getInstance().collection("students").document(user_reg_number);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students");
                Query checkUser = reference.orderByChild("username").equalTo(user_reg_number);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String passwordFromDB = dataSnapshot.child(user_reg_number).child("password").getValue(String.class);
                            if (passwordFromDB.equals(pass_word))
                                A();
                            else
                                Toast.makeText(MainActivity.this,"Incorrect Password",Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this,"Incorrect Username",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        }
        });
    }
    public void A()
    {

        Intent intent = new Intent(this, verify_reg_number.class);
        startActivity(intent);
    }
    void findViews()
    {
        reg_num = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }
}
