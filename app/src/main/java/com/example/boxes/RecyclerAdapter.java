package com.example.boxes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<UserHelper> userList;
    Context context;

    public RecyclerAdapter(Context context, ArrayList<UserHelper> userList) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singlerow, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserHelper helper = userList.get(position);
        holder.name.setText(helper.getName());
        holder.location.setText(helper.getCountry());
        holder.score.setText(helper.getHighScore());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameBoardText);
            location = itemView.findViewById(R.id.countryBoardText);
            score = itemView.findViewById(R.id.highScoreBoardText);

        }

    }
}
