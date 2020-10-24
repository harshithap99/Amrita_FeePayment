package com.example.amrita_placements.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText reg_num;
    EditText password;
    FirebaseAuth fAuth;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_test);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        findViews();
        login = (Button) findViewById(R.id.login_id);


        final TextView xzy = findViewById(R.id.FeePay);
        final TextView bob = findViewById(R.id.bob);
        final int from = ContextCompat.getColor(this, R.color.ourpink);
        final int to   = ContextCompat.getColor(this, R.color.black);

        final int from1 = ContextCompat.getColor(this, R.color.black);
        final int to1   = ContextCompat.getColor(this, R.color.white);


        final ValueAnimator anim = new ValueAnimator();


        anim.setIntValues(from, to);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                findViewById(R.id.yokk).setBackgroundColor((Integer)valueAnimator.getAnimatedValue());
            }
        });
        Integer colorFrom = getResources().getColor(R.color.black);
        Integer colorTo = getResources().getColor(R.color.white);
        final ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                xzy.setTextColor((Integer)animator.getAnimatedValue());
                bob.setTextColor((Integer)animator.getAnimatedValue());

            }

        });
        colorAnimation.setDuration(2000);
        anim.setDuration(1000);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                colorAnimation.start();
                anim.start();

                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        login.setVisibility(View.VISIBLE);
                        password.setVisibility(View.VISIBLE);
                        reg_num.setVisibility(View.VISIBLE);
                    }
                }, 1000);

            }
        }, 500);



        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final String user_reg_number = reg_num.getText().toString();
                final String pass_word = password.getText().toString();
                if (TextUtils.isEmpty(user_reg_number)) {
                    reg_num.setError("Registration number is required");
                    return;
                }
                if (TextUtils.isEmpty(pass_word)) {
                    password.setError("password is empty");
                    return;
                }
               //DocumentReference documentref = FirebaseFirestore.getInstance().collection("students").document(user_reg_number);

                DocumentReference reference = db.collection("students").document(user_reg_number);
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                String got_pass = "not got";
                                got_pass = documentSnapshot.getString("PASSWORD");
                                if (pass_word.equals(got_pass)) {

                                    A();
                                } else {
                                    password.setError("Wrong password");
                                }
                            } else {
                                reg_num.setError("Registration number is invalid");
                            }
                    }
                        else{
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                }
                });
        }
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
        System.exit(1);
    }

    public void A()
    {
        Intent intent = new Intent(this, verification.class);
        Bundle bundle = new Bundle();
        bundle.putString("user1", reg_num.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }

    @SuppressLint("WrongViewCast")
    void findViews()
    {
        reg_num = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }
}
