package com.example.a33_android_lifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton();
    }

    private void setupButton() {
        Button button = findViewById(R.id.btnNextScreen);
        button.setOnClickListener(view -> {
//            //Code that would execute when button is pressed

//            getNameAndStartScreen();
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, 11);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imgV = findViewById(R.id.imageView);
            imgV.setImageBitmap(imageBitmap);
        }
    }

    private void getNameAndStartScreen() {
        EditText edtName = findViewById(R.id.edtName);
        String userName = edtName.getText().toString();

        Toast.makeText(MainActivity.this, "Button is pressed!! User name is " + userName,
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, GreetingsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("username", userName);
        bundle.putInt("Score", 120);
        bundle.putBoolean("gender", true);
        intent.putExtra("username", userName);
        intent.putExtra("Score", 120);
        intent.putExtra("gender", true);
//            intent.putExtras(bundle);
        startActivity(intent);
    }
}