package com.example.amrita_placements.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.Collator;

public class login extends AppCompatActivity {
    Button login_btn;
    EditText reg_num;
    EditText password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        findViews();
        login_btn = findViewById(R.id.loginbutton);
        mAuth = FirebaseAuth.getInstance();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_reg_number = reg_num.getText().toString();
                String pass_word = password.getText().toString();
                if (TextUtils.isEmpty(user_reg_number)) {
                    reg_num.setError("Registration number.is.required");
                    return;
                }
                if (TextUtils.isEmpty(pass_word)) {
                    password.setError("password.is.empty");
                    return;
                }
                mAuth.signInWithEmailAndPassword(user_reg_number, pass_word).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), fragmentPage.class));
                            finish();
                        } else {
                            Toast.makeText(login.this, "error:" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
        void findViews()
    {
            reg_num = findViewById(R.id.email);
            password = findViewById(R.id.password);
            login_btn = findViewById(R.id.button);
        }
}
