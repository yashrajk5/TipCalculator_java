package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.content.Intent;
import android.content.SharedPreferences;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText amountInput = findViewById(R.id.amountInput);
        EditText tipInput = findViewById(R.id.tipInput);
        Switch roundUpSwitch = findViewById(R.id.roundUpSwitch);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button historyButton = findViewById(R.id.historyButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("TipHistory", MODE_PRIVATE);

        calculateButton.setOnClickListener(view -> {
            String amountStr = amountInput.getText().toString();
            String tipStr = tipInput.getText().toString();

            if (!amountStr.isEmpty() && !tipStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    double tipPercent = Double.parseDouble(tipStr);

                    // Calculate tip
                    double tipAmount = amount * (tipPercent / 100);

                    // Check if rounding is enabled
                    if (roundUpSwitch.isChecked()) {
                        tipAmount = new BigDecimal(tipAmount).setScale(0, RoundingMode.UP).doubleValue();
                    }

                    DecimalFormat df = new DecimalFormat("0.00");
                    String formattedTip = df.format(tipAmount);

                    // Save tip history to SharedPreferences
                    Set<String> tipHistory = sharedPreferences.getStringSet("history", new HashSet<>());
                    tipHistory.add("Amount: $" + amountStr + ", Tip: $" + formattedTip);
                    sharedPreferences.edit().putStringSet("history", tipHistory).apply();

                    // Navigate to ResultActivity
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("TIP_AMOUNT", formattedTip);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    // Handle invalid input
                }
            } else {
                // Handle empty input
            }
        });

        // Navigate to HistoryActivity on button click
        historyButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}
