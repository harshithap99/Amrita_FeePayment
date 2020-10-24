package com.example.amrita_placements.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amrita_placements.R;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    Context context;
    String re[];
    String user1;

    public Myadapter(Context ct, String r[], String user)
    {
        context = ct;
        re = r;
        user1=user;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.row, parent, false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
    holder.title.setText(re[position]);
    holder.mainLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, bills.class);
            intent.putExtra("docname", re[position]);
            intent.putExtra("user1", user1);
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return re.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titles);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
