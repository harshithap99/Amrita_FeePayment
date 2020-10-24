package com.example.amrita_placements.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Viewreceipts extends AppCompatActivity {
    RecyclerView recyclerView;
    private FirebaseFirestore db;


    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.view_receipts);
        recyclerView = findViewById(R.id.recyclerView);
        db = FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String user1 = bundle.getString("user1");

        db.collection("students").document(user1).collection("reciepts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("BROOO", document.getId() + " => " + document.getData() + document.getId());
                                i++;
                            }
                            final int j = i;
                            String records[] = new String[j];
                            i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("BROOO", document.getId() + " => " + document.getData() + document.getId());

                                records[i] = document.getId().toString();
                                i++;
                            }
                            Myadapter myadapter = new Myadapter(Viewreceipts.this, records, user1);
                            recyclerView.setAdapter(myadapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(Viewreceipts.this));
                        } else {
                            Log.d("BROOO", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
}
