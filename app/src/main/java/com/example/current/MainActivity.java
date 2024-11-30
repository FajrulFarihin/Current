package com.example.current;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button calculate;
    Button clear;
    EditText kwh;
    EditText rebate;
    TextView tvOutput;


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        calculate = findViewById(R.id.calculate);
        clear = findViewById(R.id.clear);
        kwh = findViewById(R.id.kwh);
        rebate = findViewById(R.id.rebate);
        tvOutput = findViewById(R.id.tvOutput);

        // Calculate button logic
        calculate.setOnClickListener(v -> {
            String kwhInput = kwh.getText().toString();
            String rebateInput = rebate.getText().toString();

            try {

                double totalCost = 0.0;
                double kwhValue;
                double rebatePercentage;


                if (kwhInput.isEmpty()) throw new NumberFormatException();
                kwhValue = Double.parseDouble(kwhInput);


                if (rebateInput.isEmpty()) {
                    rebatePercentage = 0.0;
                } else {
                    rebatePercentage = Double.parseDouble(rebateInput);
                    if (rebatePercentage < 0 || rebatePercentage > 5) {
                        throw new RuntimeException("Rebate Value only available between 0% - 5%.");
                    }
                    rebatePercentage = rebatePercentage / 100.0; // Convert to decimal
                }


                if (kwhValue <= 200) {
                    totalCost = kwhValue * 21.8;
                } else if (kwhValue <= 300) {
                    totalCost = (200 * 21.8) + ((kwhValue - 200) * 33.4);
                } else if (kwhValue <= 600) {
                    totalCost = (200 * 21.8) + (100 * 33.4) + ((kwhValue - 300) * 51.6);
                } else {
                    totalCost = (200 * 21.8) + (100 * 33.4) + (300 * 51.6) + ((kwhValue - 600) * 54.6);
                }

                // Convert cost
                totalCost = totalCost / 100.0;

                // Apply rebate
                double rebateAmount = totalCost * rebatePercentage;
                double finalCost = totalCost - rebateAmount;

                //result
                tvOutput.setText(String.format("Total Price: RM %.2f\nRebate Applied: RM %.2f\nFinal Price: RM %.2f",
                        totalCost, rebateAmount, finalCost));

            } catch (NumberFormatException e) {

                tvOutput.setText("Please enter valid numbers for kWh and rebate.");
                Toast.makeText(getApplicationContext(), "Invalid input. Enter numbers only.", Toast.LENGTH_LONG).show();
            } catch (RuntimeException e) {

                tvOutput.setText(e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        clear.setOnClickListener(v -> {
            kwh.setText("");
            rebate.setText("");
            tvOutput.setText("Hello");
        });

        Button aboutDeveloperButton = findViewById(R.id.aboutDeveloperButton);
        aboutDeveloperButton.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, AboutDeveloperActivity.class));
        });
    }
}
