package com.example.a34_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("username");

        TextView txtGreeting = findViewById(R.id.txtGreeting);
        txtGreeting.setText("Hello, " + username);

        findViewById(R.id.btnLogout).setOnClickListener(view -> onLogoutClicked());
    }

    private void onLogoutClicked() {
        finish();
    }
}