package com.example.amrita_placements.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.amrita_placements.fragments_registered;
import com.example.amrita_placements.fragments_courses;

class PageAdapter extends FragmentPagerAdapter {

    private int numberoftabs;
    public PageAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.numberoftabs = numberoftabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new fragments_courses();
            case 1:
                return new fragments_registered();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numberoftabs;
    }
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
