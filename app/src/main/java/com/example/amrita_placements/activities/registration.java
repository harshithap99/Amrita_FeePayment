package com.example.amrita_placements.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.Collator;

public class registration extends AppCompatActivity {

    EditText name;
    EditText registration_number;
    EditText email;
    EditText user_password;
    EditText confirm_password;
    EditText user_registration_number;
    TextView Switchtologin;
    Button register_btn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        findViews();
        register_btn = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        final String username = name.getText().toString();
                        final String user_email = email.getText().toString();
                        final String password = user_password.getText().toString();
                        final String conf_pass = confirm_password.getText().toString();
                        final String reg_num = registration_number.getText().toString();

                        if (TextUtils.isEmpty(reg_num)) {
                            email.setError("Registration number is required");
                            return;
                        }
                        if (!password.equals(conf_pass)) {
                            Toast.makeText(registration.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Intent intent = new Intent(registration.this, verify_reg_number.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username", username);
                            bundle.putString("user_email", user_email);
                            bundle.putString("password", password);
                            bundle.putString("reg_number",reg_num);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
        });
    }

    void findViews() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        user_password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirmpassword);
        user_registration_number = findViewById(R.id.regnumber);
        registration_number = findViewById(R.id.regnumber);
        Switchtologin = findViewById(R.id.switchToLogin);
    }
}
