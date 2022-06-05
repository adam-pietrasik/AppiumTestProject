package com.example.appiumtestproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<String> userRepositories;

    public RepositoryAdapter(ArrayList<String> userRepositories){
        this.userRepositories = userRepositories;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_repository, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.getView().setText(userRepositories.get(position));
    }

    @Override
    public int getItemCount() {
        return userRepositories.size();
    }

    public void setItems(ArrayList<String> userRepositories){
        this.userRepositories = userRepositories;
    }
}
