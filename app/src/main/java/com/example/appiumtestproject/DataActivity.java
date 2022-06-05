package com.example.appiumtestproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;

public class DataActivity extends AppCompatActivity implements JSONReaderCallback {

    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private ArrayList<String> repositories = new ArrayList<>();

    private String userName;
    private EditText searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        initRecyclerView();
        searchET = findViewById(R.id.searchEditText);

        Button searchBtn = findViewById(R.id.searchButtonId);
        searchBtn.setOnClickListener(l -> {
            userName = searchET.getText().toString();
            if(userName.isEmpty()){
                Toast.makeText(this, "Enter repository owner", Toast.LENGTH_SHORT).show();
            }
            else{
                readData();
            }
        });

    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.dataRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RepositoryAdapter(repositories);
        recyclerView.setAdapter(adapter);
    }
    
    private void readData(){
        String githubAPI = "https://api.github.com/users/" + userName + "/repos";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONReader jsonReader = new JSONReader(githubAPI, requestQueue, this);
        jsonReader.githubAPIArray();
    }

    @Override
    public void onSuccess(ArrayList<String> githubAPIData) {
        adapter.setItems(githubAPIData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(ArrayList<String> githubAPIData){
        Toast.makeText(this, "User doesn't exists", Toast.LENGTH_SHORT).show();
    }
}