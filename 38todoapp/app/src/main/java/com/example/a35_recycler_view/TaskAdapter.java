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

import com.example.a35_recycler_view.data.Todo;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private static final String TAG = "TaskAdapter";
    private final List<Todo> data;
    private OnTodoItemClickedListener listener;

    public TaskAdapter(List<Todo> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewSingleRowElement =
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(viewSingleRowElement);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Todo task = data.get(position);
        holder.bindData(task);
        holder.itemView.findViewById(R.id.imgDelete).setOnClickListener(view -> {
                removeRow(position);
        });

        holder.itemView.findViewById(R.id.imgDone).setOnClickListener(view -> {
            task.setDone(!task.isDone());
            holder.changeDoneView(task);
        });

        //I removed all hardcoded transition names from the xml layout files. Transition names need to
        // be unique. That's why they need to be set programatically, so every row has its own
        // transition name.
        holder.itemView.findViewById(R.id.txtTitle).setTransitionName("transition_view_" + position);

        //Added the shared view to the arguments of onTodoItemClicked. This way TodoFragment would have access to it
        // and can see what is the transition name of that view.
        holder.itemView.setOnClickListener(view -> listener.onTodoItemClicked(task, holder.itemView.findViewById(R.id.txtTitle)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void removeRow(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnRowClickedListener(OnTodoItemClickedListener listener) {
        this.listener = listener;
    }

    public interface OnTodoItemClickedListener {
        void onTodoItemClicked(Todo todoItem, View sharedView);
    }
}
