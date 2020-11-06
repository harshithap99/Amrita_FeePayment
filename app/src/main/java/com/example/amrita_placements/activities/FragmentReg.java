package com.example.amrita_placements.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amrita_placements.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FragmentReg extends Fragment {
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private List<assigner> listregistered;
    String user;
    View v;

    public FragmentReg(String user1, List<assigner> reg)
    {
            user = user1;
            listregistered = reg;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tabregistered,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.regrview);
        CoursesAdapter coursesAdapter = new CoursesAdapter(getContext(),listregistered,user,0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(coursesAdapter);
        return v;
    }
}
