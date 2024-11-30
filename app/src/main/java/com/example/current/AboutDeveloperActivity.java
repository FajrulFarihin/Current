package com.example.current;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AboutDeveloperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about_developer);


        Button backToMainButton = findViewById(R.id.backToMain);


        backToMainButton.setOnClickListener(v -> {
            Intent intent = new Intent(AboutDeveloperActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
