package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText amountInput = findViewById(R.id.amountInput);
        EditText tipInput = findViewById(R.id.tipInput);
        Switch roundUpSwitch = findViewById(R.id.roundUpSwitch);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultText = findViewById(R.id.resultText);

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

                    // Display result
                    resultText.setText("Tip Amount: $" + df.format(tipAmount));
                } catch (NumberFormatException e) {
                    resultText.setText("Invalid input, please enter valid numbers.");
                }
            } else {
                resultText.setText("Please enter both amount and tip percentage.");
            }
        });
    }
}
