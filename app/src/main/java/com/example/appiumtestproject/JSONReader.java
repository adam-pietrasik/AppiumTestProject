package com.example.appiumtestproject;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;

public class JSONReader {

    private String githubAPI;
    private RequestQueue requestQueue;
    private JSONReaderCallback jsonReaderCallback;

    public JSONReader(String githubAPI, RequestQueue requestQueue, JSONReaderCallback jsonReaderCallback){
        this.githubAPI = githubAPI;
        this.requestQueue = requestQueue;
        this.jsonReaderCallback = jsonReaderCallback;
    }

    public void githubAPIArray(){
        ArrayList<String> repositories = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, githubAPI, null,
                response -> {
                    for (int i = 0; i < response.length(); ++i) {
                        try {
                            JSONObject repository = response.getJSONObject(i);
                            String repositoryName = repository.getString("name");
                            repositories.add(repositoryName);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    jsonReaderCallback.onSuccess(repositories);
                },
                error -> {
                    error.printStackTrace();
                    repositories.clear();
                    jsonReaderCallback.onError(repositories);
                });
        requestQueue.add(request);
    }

}