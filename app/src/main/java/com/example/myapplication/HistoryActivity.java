package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Intent;
import java.util.Set;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView historyText = findViewById(R.id.historyText);
        Button backButton = findViewById(R.id.backButton);

        // Retrieve tip history from SharedPreferences
        sharedPreferences = getSharedPreferences("TipHistory", MODE_PRIVATE);
        Set<String> tipHistory = sharedPreferences.getStringSet("history", null);

        // Display the history
        if (tipHistory != null && !tipHistory.isEmpty()) {
            StringBuilder history = new StringBuilder("Tip History:\n");
            for (String entry : tipHistory) {
                history.append(entry).append("\n");
            }
            historyText.setText(history.toString());
        } else {
            historyText.setText("No tip history available.");
        }

        // Back button functionality
        backButton.setOnClickListener(view -> {
            finish(); // Finish and return to MainActivity
        });
    }
}
