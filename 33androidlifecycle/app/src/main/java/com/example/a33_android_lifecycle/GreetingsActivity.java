package com.example.a33_android_lifecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GreetingsActivity extends AppCompatActivity {

    private static final String TAG = "GreetingsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
        Log.d(TAG, "onCreate");

        Intent startingIntent = getIntent();
        String username = startingIntent.getStringExtra("username");

        Toast.makeText(this, "The name of the user is " + username, Toast.LENGTH_SHORT).show();
        TextView txtName = findViewById(R.id.txtName);
        txtName.setText("Greetings, " + username);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                Intent intent = new Intent(GreetingsActivity.this, MainActivity.class);
//                startActivity(intent);

//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);

//                Intent i = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.vogella.com/"));
//                startActivity(i);
            }
        }, 1000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");


        super.onDestroy();
    }
}
