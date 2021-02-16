package com.example.a35_recycler_view.data;

public class Todo {

    private String title;
    private String text;
    private int priority;
    private int difficulty;
    private long dueDate;
    private boolean isDone;

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, String text, int priority, int difficulty, long dueDate) {
        this.title = title;
        this.text = text;
        this.priority = priority;
        this.difficulty = difficulty;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public long getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public interface Priority {
        int LOW = 1;
        int MEDIUM = 2;
        int HIGH = 3;
    }

    public interface Difficulty {
        int LOW = 1;
        int MEDIUM = 2;
        int HIGH = 3;
    }
}
