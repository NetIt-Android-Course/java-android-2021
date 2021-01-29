package com.example.a35_recycler_view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private static final String TAG = "TaskAdapter";
    private final List<Task> data;

    public TaskAdapter(List<Task> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewSingleRowElement =
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.task_item, parent, false);
        TaskViewHolder vh = new TaskViewHolder(viewSingleRowElement);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = data.get(position);
        holder.bindData(task);
        holder.itemView.findViewById(R.id.txtTitle).setOnClickListener(view -> {
            if(!task.isDone()) {
                task.setDone(true);
                holder.changeDoneView(task);
            } else {
                removeRow(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void removeRow(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
}
