package com.example.amrita_placements.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.amrita_placements.R;
import com.example.amrita_placements.activities.login;

public class MainActivity extends AppCompatActivity {
    Button signupb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_test);
        signupb = (Button) findViewById(R.id.signup_id);
        signupb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            A();
            }
        });
    }

    public void A()
    {
        Toast toast = Toast.makeText(getApplicationContext(),
                "This is a message displayed in a Toast",
                Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, registration.class);
        startActivity(intent);
    }
}
