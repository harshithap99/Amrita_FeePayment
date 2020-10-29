package com.example.amrita_placements.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.amrita_placements.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class bills extends AppCompatActivity {


    private FirebaseFirestore db;
    FirebaseAuth fAuth;
    TextView name,regno,mobile,paytype,accnumber,accname,pamount,feetype,feeamt,paidamt,paymod,paydate;
    String emailid;
    Button download,mail;
    private static String FILE = Environment.getExternalStorageDirectory()
            + "/HelloWorld.pdf";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.billpage);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.sname);
        regno = findViewById(R.id.regno);
        feetype = findViewById(R.id.feetype);
        accnumber = findViewById(R.id.batch);
        pamount = findViewById(R.id.paidamt);
        feeamt = findViewById(R.id.feeamt);
        paymod = findViewById(R.id.paymod);
        paydate = findViewById(R.id.paydate);

        download = findViewById(R.id.download);
        mail = findViewById(R.id.mail);

        PdfDocument doc = new PdfDocument();
        Paint myPaint = new Paint();



        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String docname = bundle.getString("docname");
        String user1 = bundle.getString("user1");


        Toast.makeText(getApplicationContext(), user1, Toast.LENGTH_SHORT).show();
        DocumentReference reference = db.collection("students").document(user1).collection("reciepts").document(docname);


        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {


                        name.setText(documentSnapshot.getString("Student_name"));
                        regno.setText(documentSnapshot.getString("Student_reg"));
                        // mobile.setText(documentSnapshot.getString("mobile"));
                        accnumber.setText(documentSnapshot.getString("acc_number"));
                        pamount.setText(documentSnapshot.getString("amt"));
                        paymod.setText(documentSnapshot.getString("pay_type"));
                        paydate.setText(documentSnapshot.getString("date"));
                        feetype.setText(documentSnapshot.getString("fee_type"));
                        feeamt.setText(documentSnapshot.getString("amt"));
                        emailid = documentSnapshot.getString("emailid");
                        download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                == PackageManager.PERMISSION_GRANTED) {
                                            Log.v("TAG","Permission is granted");
                                        } else {

                                            Log.v("TAG","Permission is revoked");
                                            ActivityCompat.requestPermissions(bills.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                                        }
                                    }
                                    else { //permission is automatically granted on sdk<23 upon installation
                                        Log.v("TAG","Permission is granted");
                                    }


                                    // create a new document
                                    PdfDocument document = new PdfDocument();

                                    // crate a page description
                                    PdfDocument.PageInfo pageInfo =
                                            new PdfDocument.PageInfo.Builder(500, 700, 1).create();

                                    // start a page
                                    PdfDocument.Page page = document.startPage(pageInfo);

                                    Canvas canvas = page.getCanvas();

                                    Paint paint = new Paint();
                                    paint.setColor(Color.BLACK);
                                    paint.setTextSize(16);
                                    canvas.drawText("Name:" + name.getText(), 50, 50, paint);
                                    canvas.drawText("Registration Number:" +  regno.getText(), 50, 70, paint);
                                    canvas.drawText("Account Number:" +  accnumber.getText(), 50, 90, paint);
                                    canvas.drawText("Due Fees:" +  pamount.getText(), 50, 110, paint);
                                    canvas.drawText("Paid fees:" +  feeamt.getText(), 50, 150, paint);
                                    canvas.drawText("Date of payment:" +  paydate.getText(), 50, 170, paint);
                                    canvas.drawText("Payment Mode:" +  paymod.getText(), 50, 190, paint);
                                    canvas.drawText("Payment Status:" , 50, 210, paint);
                                    paint.setColor(Color.RED);
                                    paint.setTextSize(46);
                                    canvas.drawText("PAID" , 50, 290, paint);

                                // finish the page
                                    document.finishPage(page);



                                    // write the document content
                                    String targetPdf = "/Internal storage/Documents/test.pdf";
                                    File filePath = new File(targetPdf);
                                    try {
                                        //document.writeTo(new FileOutputStream(filePath));
                                        document.writeTo(new FileOutputStream(FILE));
                                        Toast.makeText(bills.this, "Downloaded to " + FILE.toString(), Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        Toast.makeText(bills.this, "Something wrong: " + e.toString(),
                                                Toast.LENGTH_LONG).show();
                                    }

                                    // close the document
                                    document.close();
                                    onBackPressed();

                            }
                        });



                        mail.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                String editTextEmail,editTextSubject,editTextMessage;
                                editTextEmail = emailid;
                                editTextSubject = "PAYMENT RECIEPT" + paydate.toString();
                                editTextMessage = "Name: " + name.toString() + "\nRegisteration Number: " + regno.toString() + "\nAccount Number: " + accnumber.toString()
                                        + "\nPayment Type: " + paymod.toString() + "\nPay Amount: " + pamount.toString() + "\nPaid Date: " + paydate.toString() + "\nPayment Status: " + "PAID and Recorded";
                                String email = editTextEmail.toString().trim();
                                String subject = editTextSubject.toString().trim();
                                String message = editTextMessage.toString().trim();

                                JavaMailAPI javaMailAPI = new JavaMailAPI(bills.this,email,subject,message);
                                javaMailAPI.execute();
                                onBackPressed();
                                Toast.makeText(bills.this, "Sent to " + editTextEmail, Toast.LENGTH_LONG).show();
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