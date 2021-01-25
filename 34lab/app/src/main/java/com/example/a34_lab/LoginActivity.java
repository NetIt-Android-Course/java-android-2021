package com.example.a34_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(view -> onLoginClicked());
    }

    private void onLoginClicked() {
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if(username.equals("Gosho") && password.equals("1234")) {
            //Login the user
            Intent nextScreenIntent = new Intent(this, MainActivity.class);
            nextScreenIntent.putExtra("username", username);
            startActivity(nextScreenIntent);
            finish();
        } else {
            //Show error
            Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
        }
    }
}