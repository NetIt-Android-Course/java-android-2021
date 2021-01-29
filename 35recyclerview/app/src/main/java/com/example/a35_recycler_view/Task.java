package com.example.a35_recycler_view;

public class Task {

    public static final int DIFFICULTY_EASY = 1;
    public static final int DIFFICULTY_MEDIUM = 2;
    public static final int DIFFICULTY_HARD = 3;

    private String title;
    private int difficulty;
    private boolean isDone;

    public Task(String title, int difficulty) {
        this.title = title;
        this.difficulty = difficulty;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
