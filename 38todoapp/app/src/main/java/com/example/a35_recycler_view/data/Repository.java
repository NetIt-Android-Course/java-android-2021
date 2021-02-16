package com.example.a35_recycler_view.data;

import com.example.a35_recycler_view.Task;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository instance;

    public static Repository getInstance() {
        if(instance == null) instance = new Repository();
        return instance;
    }

    private List<Todo> todos;

    private Repository() {
        todos = new ArrayList<>();
        todos.add(new Todo("Wash dog", "The dog needs a good bath. Warm the water and wash it.",
                Todo.Priority.MEDIUM, Todo.Difficulty.HIGH, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));

        todos.add(new Todo("Clean house", "The dog needs a good bath. Warm the water and wash it.",
                Todo.Priority.MEDIUM, Todo.Difficulty.HIGH, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));

        todos.add(new Todo("Take out trash", "The dog needs a good bath. Warm the water and wash it.",
                Todo.Priority.MEDIUM, Todo.Difficulty.HIGH, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));

        todos.add(new Todo("Paint!", "The dog needs a good bath. Warm the water and wash it.",
                Todo.Priority.MEDIUM, Todo.Difficulty.HIGH, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));

        todos.add(new Todo("Get some salt from the neightbours", "The dog needs a good bath. Warm the water and wash it.",
                Todo.Priority.MEDIUM, Todo.Difficulty.HIGH, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));

        todos.add(new Todo("Do stuff!", "The dog needs a good bath. Warm the water and wash it.",
                Todo.Priority.MEDIUM, Todo.Difficulty.HIGH, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));
    }

    public Todo getTodo(int index) {
        return todos.get(index);
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public List<Todo> getAll() {
        return todos;
    }
}
