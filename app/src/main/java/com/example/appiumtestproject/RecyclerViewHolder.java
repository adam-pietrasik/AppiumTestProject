package com.example.appiumtestproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView userRepository;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        userRepository = itemView.findViewById(R.id.userRepositoryId);
    }

    public TextView getView(){
        return this.userRepository;
    }
}
