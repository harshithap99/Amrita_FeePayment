package com.example.amrita_placements.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.amrita_placements.R;
import com.example.amrita_placements.adapters.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class fragmentPageCir extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1c,tab2c;
    public PageAdapter pageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cir_fragment);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tab1c = (TabItem) findViewById(R.id.Tab1);
        tab2c = (TabItem) findViewById(R.id.Tab2);

        viewPager = findViewById(R.id.viewpager);

        pageradapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageradapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                {
                    pageradapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==1)
                {
                    pageradapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener((new TabLayout.TabLayoutOnPageChangeListener(tabLayout)));


    }
}

