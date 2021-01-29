package com.example.a35_recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        Task task1 = new Task("Wash dishes", Task.DIFFICULTY_EASY);
        Task task2 = new Task("Clean house", Task.DIFFICULTY_MEDIUM);
        Task task3 = new Task("Take out trash", Task.DIFFICULTY_EASY);
        Task task4 = new Task("Walk the dog", Task.DIFFICULTY_HARD);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        for (int i = 0; i < 50; i++) {
            tasks.add(new Task("Task " + (5 + i), Task.DIFFICULTY_MEDIUM));
        }

        RecyclerView recyclerView = findViewById(R.id.recTodo);
        TaskAdapter adapter = new TaskAdapter(tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}