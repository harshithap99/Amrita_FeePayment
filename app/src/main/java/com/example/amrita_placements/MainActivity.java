package com.example.amrita_placements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cir_fragment);
        String s = "HarshiSucksButGeorgeisAwesomeTHO";
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
