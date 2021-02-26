package com.example.a35_recycler_view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a35_recycler_view.data.Todo;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtTitle;
    private final View viewDifficulty;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        viewDifficulty = itemView.findViewById(R.id.viewDifficulty);
    }

    public void bindData(Todo task) {
        txtTitle.setText(task.getTitle());

        int difficultyColor = ConstantToResConverter.getDifficultyColor(task);
        Context context = itemView.getContext();
        viewDifficulty.setBackgroundColor(context.getColor(difficultyColor));
        changeDoneView(task);
    }

    public void changeDoneView(Todo task) {
        if (task.isDone()) {
            ((ImageView) itemView.findViewById(R.id.imgDone)).setImageResource(R.drawable.ic_checked);
        } else {
            ((ImageView) itemView.findViewById(R.id.imgDone)).setImageResource(R.drawable.ic_uncheked);
        }
    }
}
