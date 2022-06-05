package com.example.appiumtestproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView messageTextView;
    private EditText messageEditText;
    private Button enterMessageButton;
    private Button clearMessageButton;
    private Button goToAnotherActivityButton;
    private Button goToRecyclerViewButton;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        enterMessageButton.setOnClickListener(l -> {
            message = messageEditText.getText().toString();
            if(message.isEmpty()){
                Toast.makeText(this, "Enter message", Toast.LENGTH_SHORT).show();
            }
            else{
                messageTextView.setText(message);
            }
        });

        clearMessageButton.setOnClickListener(l -> {
            if (messageTextView.getText().toString().isEmpty()){
                Toast.makeText(this, "Message field is empty", Toast.LENGTH_SHORT).show();
            }
            else{
                messageTextView.setText("");
            }
        });

        goToAnotherActivityButton.setOnClickListener(l -> {
            startNewActivity(SecondActivity.class);
        });

        goToRecyclerViewButton.setOnClickListener(l -> {
            startNewActivity(DataActivity.class);
        });
    }

    private void startNewActivity(Class<?> activityClass){
        Intent intent = new Intent(this, activityClass);


        if(activityClass.equals(SecondActivity.class)){
            activityResultLauncher.launch(intent);
        }
        else{
            startActivity(intent);
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    messageTextView.setText(bundle.getString("Subactivity_message"));
                }
            }
    );

    private void initialize(){
        messageEditText = findViewById(R.id.enterMessageId);
        messageTextView = findViewById(R.id.messageTvId);
        enterMessageButton = findViewById(R.id.enterMessageButtonId);
        clearMessageButton = findViewById(R.id.clearMessageButtonId);
        goToAnotherActivityButton = findViewById(R.id.goToActivityButtonId);
        goToRecyclerViewButton = findViewById(R.id.goToRecyclerViewId);
    }

}