package com.example.a35_recycler_view.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {

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

    protected Todo(Parcel in) {
        title = in.readString();
        text = in.readString();
        priority = in.readInt();
        difficulty = in.readInt();
        dueDate = in.readLong();
        isDone = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text);
        dest.writeInt(priority);
        dest.writeInt(difficulty);
        dest.writeLong(dueDate);
        dest.writeByte((byte) (isDone ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

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

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", priority=" + priority +
                ", difficulty=" + difficulty +
                ", dueDate=" + dueDate +
                ", isDone=" + isDone +
                '}';
    }
}
