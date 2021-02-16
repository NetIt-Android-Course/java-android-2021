package com.example.a35_recycler_view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a35_recycler_view.R;
import com.example.a35_recycler_view.TaskAdapter;
import com.example.a35_recycler_view.data.Repository;


public class TodoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnCreateTodo).setOnClickListener(view1 -> openCreateTodoScreen(view1));

        RecyclerView recyclerView = view.findViewById(R.id.recView);
        TaskAdapter adapter = new TaskAdapter(Repository.getInstance().getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void openCreateTodoScreen(View view1) {
        Navigation.findNavController(view1).navigate(R.id.action_todoFragment_to_createTodoFragment);
    }
}