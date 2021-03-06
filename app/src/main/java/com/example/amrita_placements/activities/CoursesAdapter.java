package com.example.amrita_placements.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amrita_placements.R;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder1> {

    Context mcontext;
    List<assigner> mdata;
    String user1;
    int flag1;

    public CoursesAdapter(Context ct, List<assigner> Mdata, String user, int flag)
    {
        mcontext = ct;
        mdata = Mdata;
        user1=user;
        flag1=flag;
    }
    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);

        if(flag1==1) { //courses
            View view = inflater.inflate(R.layout.courseitem, parent, false);
            return new MyViewHolder1(view);
        }
        else{
            View view = inflater.inflate(R.layout.regitem, parent, false);
            return new MyViewHolder1(view);
        }



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, final int position) {
        holder.title.setText(mdata.get(position).getTitle());
        holder.des.setText(mdata.get(position).getDescription());




            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent(mcontext, Coursesfees.class);
                    Intent intent = new Intent(mcontext, verification.class);

                    if(flag1 == 1)
                    {
                        final Bundle bundle1 = new Bundle();
                        bundle1.putString("user1", user1);
                        bundle1.putString("title", mdata.get(position).getTitle());
                        intent1.putExtras(bundle1);
                        mcontext.startActivity(intent1);
                    }
                    else
                    {
                        final Bundle bundle1 = new Bundle();
                        bundle1.putString("user1", user1);

                        intent.putExtras(bundle1);
                        mcontext.startActivity(intent);
                    }

                    // intent.putExtra("docname", re[position]);

                }
            });





    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        TextView title,des;
        LinearLayout mainLayout;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.coursename);
            title = itemView.findViewById(R.id.coursename);
            des = itemView.findViewById(R.id.coursetype);
            mainLayout = itemView.findViewById(R.id.courserow);
        }
    }
}
