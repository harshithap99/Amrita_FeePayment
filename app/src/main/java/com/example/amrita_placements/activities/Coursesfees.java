package com.example.amrita_placements.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class Coursesfees extends AppCompatActivity {

    Button formid;
    Button payid;
    TextView AMT;
    int flag = 0;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.coursesfeespage);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");
        final String Title = bundle.getString("title");

        TextView title = (TextView) findViewById(R.id.Coursetitlee);
        title.setText(Title);

        final TextView amont = (TextView) findViewById(R.id.amountt);
        final TextView t1 = (TextView) findViewById(R.id.editTextTextMultiLine2);
        final TextView t2 = (TextView) findViewById(R.id.textView6);
        final TextView t3 = (TextView) findViewById(R.id.textView8);
        final TextView t4 = (TextView) findViewById(R.id.textView2);
        final TextView t5 = (TextView) findViewById(R.id.textView3);
        final TextView t6 = (TextView) findViewById(R.id.textView4);


        final Button formid = (Button) findViewById(R.id.form_id);
        final Button payid = (Button) findViewById(R.id.pay_id);
        final Button backid = (Button) findViewById(R.id.form_id2);



        db.collection("students").document(user1).collection("workshops")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("BROOO", document.getId() + " => " + document.getData() + document.getId());

                                if(document.getId().toString().equals(Title)) {
                                    flag =1;

                                }

                            }


                            backid.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Coursesfees.this, courses.class);

                                    final Bundle bundle1 = new Bundle();
                                    bundle1.putString("user1", user1);

                                    intent.putExtras(bundle1);
                                    Coursesfees.this.startActivity(intent);

                                }
                            });


                            if(flag==1)
                            {
                                amont.setText("Already Registered");
                                amont.setTextColor(Color.WHITE);
                                amont.setTextSize(19);
                                t1.setVisibility(View.INVISIBLE);
                                t2.setVisibility(View.INVISIBLE);
                                t3.setVisibility(View.INVISIBLE);
                                t4.setVisibility(View.INVISIBLE);
                                t5.setVisibility(View.INVISIBLE);
                                t6.setVisibility(View.INVISIBLE);
                                formid.setVisibility(View.INVISIBLE);
                                payid.setVisibility(View.INVISIBLE);
                                backid.setVisibility(View.VISIBLE);

                            }
                            else
                            {

                            }


                        } else {
                            Log.d("BROOO", "Error getting documents: ", task.getException());
                        }
                    }
                });





    }



}
