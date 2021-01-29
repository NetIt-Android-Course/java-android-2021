package com.example.a35_recycler_view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtTitle;
    private final View viewDifficulty;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        viewDifficulty = itemView.findViewById(R.id.viewDifficulty);
    }

    public void bindData(Task task) {
        txtTitle.setText(task.getTitle());

        int difficultyColor = getDifficultyColor(task);
        Context context = itemView.getContext();
        viewDifficulty.setBackgroundColor(context.getColor(difficultyColor));
        changeDoneView(task);
    }

    public void changeDoneView(Task task) {
        if(task.isDone()) {
            itemView.findViewById(R.id.txtDone).setVisibility(View.VISIBLE);
        } else {
            itemView.findViewById(R.id.txtDone).setVisibility(View.INVISIBLE);
        }
    }

    private int getDifficultyColor(Task task) {
        int difficultyColor = 0;
        switch (task.getDifficulty()) {
            case Task.DIFFICULTY_EASY:
                difficultyColor = R.color.easy_green;
                break;
            case Task.DIFFICULTY_MEDIUM:
                difficultyColor = R.color.medium_yellow;
                break;
            case Task.DIFFICULTY_HARD:
                difficultyColor = R.color.hard_red;
                break;
        }
        return difficultyColor;
    }
}
