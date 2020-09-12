package com.example.amrita_placements.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.amrita_placements.R;
import com.example.amrita_placements.activities.login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_test);

    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutid:
                Intent myIntent = new Intent(this, login.class);
                startActivity(myIntent);
                break;
            case R.id.id1:
                Intent myIntent2 = new Intent(this, login.class);
                startActivity(myIntent2);
                break;
        }
        return true;
    }


}
