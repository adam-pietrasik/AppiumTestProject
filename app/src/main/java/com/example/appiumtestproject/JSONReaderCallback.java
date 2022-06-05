package com.example.appiumtestproject;

import java.util.ArrayList;

public interface JSONReaderCallback {
    void onSuccess(ArrayList<String> githubAPIData);
    void onError(ArrayList<String> githubAPIData);
}

