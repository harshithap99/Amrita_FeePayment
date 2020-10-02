package com.example.amrita_placements.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class changepassword extends AppCompatActivity {
    Button changepassword;
    EditText currentpassword;
    EditText newpassword;
    EditText newpassword1;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    final FirebaseUser this_user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.change_passowrd);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        findViews();
        changepassword = findViewById(R.id.changepassword);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                final String user_current_password = currentpassword.getText().toString();
                final String user_new_password = newpassword.getText().toString();
                final String user_new_password1 = newpassword1.getText().toString();
                if (Objects.equals(user_new_password, user_new_password1)) {
                    DocumentReference reference = db.collection("students").document(this_user.getUid());
                    reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists())
                            {
                                String got_pass = "not got";
                                got_pass = documentSnapshot.getString("PASSWORD");
                                if (user_current_password.equals(got_pass))
                                {
                                    go_to_homepage();
                                    // we need to store the user_new_password in the password of the user
                                }
                                else {
                                    currentpassword.setError("Wrong password");
                                }
                            }
                        }
                    });
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