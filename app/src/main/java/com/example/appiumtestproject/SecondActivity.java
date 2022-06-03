package com.example.appiumtestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendMessageButton;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialize();

        sendMessageButton.setOnClickListener(l -> {
            message = messageEditText.getText().toString();
            if (message.isEmpty()){
                Toast.makeText(this, "Nothing to send!", Toast.LENGTH_SHORT).show();
            }
            else{
                sendBackToMainActivity(message);
            }
        });
    }

    private void initialize(){
        messageEditText = findViewById(R.id.editTextMessageId);
        sendMessageButton = findViewById(R.id.sendTextBackButtonId);
    }

    private void sendBackToMainActivity(String message){
        Intent mainActivity = new Intent();
        mainActivity.putExtra("Subactivity_message", message);
        setResult(RESULT_OK, mainActivity);
        finish();
    }
}