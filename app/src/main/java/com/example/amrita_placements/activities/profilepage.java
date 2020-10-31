package com.example.amrita_placements.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class profilepage extends AppCompatActivity {
    Button Change_password;
    Button View_receipts;
    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    final FirebaseUser this_user = FirebaseAuth.getInstance().getCurrentUser();
    StorageReference gsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile_page);
        Change_password = findViewById(R.id.button);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        View_receipts = findViewById(R.id.view_receipts);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        String name = bundle.getString("name");
        String phone = bundle.getString("phone");
        String email = bundle.getString("email");
        String pic = bundle.getString("picloc");

        TextView nam = findViewById(R.id.nam);
        TextView reg = findViewById(R.id.reg);
        TextView mail = findViewById(R.id.mailid);

        nam.setText(name);
        reg.setText(user1);
        mail.setText(email);


        Change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_change_password();
            }
        });
        View_receipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_view_receipts();
            }
        });

        final ImageView propic = findViewById(R.id.propic);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        gsReference = storage.getReference().child("propic/"+pic);
        try {
            final File here = File.createTempFile("george","jpg");
            gsReference.getFile(here).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(here.getAbsolutePath());
                    propic.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "WAAHAHHAA23312312A", Toast.LENGTH_SHORT).show();
                    Log.e("HEEEREEEEEEE0000", "BROOOOOOOOOOOOOO");
                }
            });

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "WAAHAHHAAAA", Toast.LENGTH_SHORT).show();
            Log.e("HEEEREEEEEEE9999999999", "BROOOOOOOOOOOOOO");
            e.printStackTrace();

        }



        /*DocumentReference reference = db.collection("students").document(user1);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {

                    final ImageView propic = findViewById(R.id.propic);
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String pic = documentSnapshot.getString("picloc");


                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/









    }
    public void go_to_change_password()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, changepassword.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        intent.putExtras(bundle1);
        startActivity(intent);
        this.finish();
    }
    public void go_to_view_receipts()
    {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String user1 = bundle.getString("user1");
        Intent intent = new Intent(this, Viewreceipts.class);
        final Bundle bundle1 = new Bundle();
        bundle1.putString("user1", user1);
        intent.putExtras(bundle1);
        startActivity(intent);
    }
}
