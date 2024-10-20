package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tipResult = findViewById(R.id.tipResult);
        Button backButton = findViewById(R.id.backButton);

        // Get the intent that started this activity and extract the tip amount
        Intent intent = getIntent();
        String tipAmount = intent.getStringExtra("TIP_AMOUNT");

        // Display the tip amount
        tipResult.setText("Tip Amount: $" + tipAmount);

        // Back button functionality
        backButton.setOnClickListener(view -> {
            finish(); // Finish this activity and return to the previous one
        });
    }
}
