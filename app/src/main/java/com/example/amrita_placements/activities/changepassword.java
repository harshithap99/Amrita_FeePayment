package com.example.amrita_placements.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import android.os.Vibrator;
import android.widget.Toast;

public class changepassword extends AppCompatActivity {
    Button changepassword;
    EditText currentpassword;
    EditText newpassword;
    EditText newpassword1;
    String pass;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.change_passowrd);
        db = FirebaseFirestore.getInstance();
        findViews();
        changepassword = findViewById(R.id.changepassword);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");


        changepassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                final String user_current_password = currentpassword.getText().toString();
                final String user_new_password = newpassword.getText().toString();
                final String user_new_password1 = newpassword1.getText().toString();
                if (TextUtils.isEmpty(user_current_password)) {
                    currentpassword.setError("Current password is required");
                    v.vibrate(100);

                    return;
                }
                if (TextUtils.isEmpty(user_new_password)) {
                    newpassword.setError("New password is required");
                    v.vibrate(100);

                    return;
                }
                if (TextUtils.isEmpty(user_new_password1)) {
                    newpassword1.setError("conformation of new password is required");
                    v.vibrate(100);

                    return;
                }
                if (Objects.equals(user_new_password, user_new_password1)) {
                    assert user1 != null;
                    DocumentReference reference = db.collection("students").document(user1);

                    reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful())
                            {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if (documentSnapshot.exists()) {


                                    pass = documentSnapshot.getString("PASSWORD");
                                    if (documentSnapshot.getString("PASSWORD").equals(currentpassword.getText().toString())) {
                                        Map<String, Object> passd  = new HashMap<>();

                                        passd.put("PASSWORD", user_new_password);

                                        db.collection("students").document(user1)
                                                .set(passd, SetOptions.merge());

                                        Toast.makeText(getApplicationContext(), "Password Changed!", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    } else
                                    {
                                        Toast.makeText(getApplicationContext(), "Old password wrong!"+pass+currentpassword.toString(), Toast.LENGTH_SHORT).show();

                                        v.vibrate(100);
                                        return;
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "DOC NOT FOUND", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Passwords dont match!", Toast.LENGTH_SHORT).show();
                    v.vibrate(100);
                    return;
                }
            }
        });
    }
    public void go_to_homepage()
    {
        Intent intent = new Intent(this, verification.class);
        startActivity(intent);
    }
    void findViews()
    {
        currentpassword = findViewById(R.id.current_password);
        newpassword = findViewById(R.id.change_password);
        newpassword1 = findViewById(R.id.confirm_password);


    }
  }