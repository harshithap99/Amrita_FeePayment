package com.example.amrita_placements.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class NeftProcessing extends AppCompatActivity {
    long neft_status =-1;
    Button button;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.neft_processing);
        retrieve_values();



    }

    public void go_home()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        String type = bundle.getString("type");
        db = FirebaseFirestore.getInstance();


        
        if(neft_status==1)
        {
            //accepted
            Map<String, Object> aftertask  = new HashMap<>();
            aftertask.put(type+"neftflag", false);
            aftertask.put(type+"neftaccepted", -1);
            aftertask.put(type, 0);

            db.collection("students").document(user1)
                    .set(aftertask, SetOptions.merge());
        }
        else if(neft_status==0)
        {
            //rejected

            Map<String, Object> aftertask  = new HashMap<>();
            aftertask.put(type+"neftflag", false);
            aftertask.put(type+"neftaccepted", -1);


            db.collection("students").document(user1)
                    .set(aftertask, SetOptions.merge());
        }

        Intent intent = new Intent(this, verification.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        intent.putExtras(bundle1);
        startActivity(intent);
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
                        Bundle bundle = getIntent().getExtras();
                        assert bundle != null;
                        String user1 = bundle.getString("user1");
                        String type = bundle.getString("type");
                        neft_status = (long) documentSnapshot.get(type+"neftaccepted");
                        String g = Long.toString(neft_status);
                        Toast.makeText(getApplicationContext(), g, Toast.LENGTH_LONG).show();
                        if(neft_status==1)
                        {
                            setContentView(R.layout.neftaccepted);
                        }
                        else if(neft_status==0)
                        {
                            setContentView(R.layout.neftrejected);
                        }
                        else {
                            setContentView(R.layout.neft_processing);
                        }

                        button = findViewById(R.id.button);

                        button.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                go_home();
                            }
                        });


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
