package com.example.a35_recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSpinner();
    }



    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.spnMenuCategory);

        List<String> menuCategories = new ArrayList<>();
        menuCategories.add("Breakfast");
        menuCategories.add("Lunch");
        menuCategories.add("Dinner");
        menuCategories.add("Drinks");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, menuCategories);

        spinner.setAdapter(spinnerAdapter);
    }
}