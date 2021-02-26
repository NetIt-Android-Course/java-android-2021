package com.example.a35_recycler_view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a35_recycler_view.R;
import com.example.a35_recycler_view.TaskAdapter;
import com.example.a35_recycler_view.data.Repository;
import com.example.a35_recycler_view.data.Todo;


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
        adapter.setOnRowClickedListener(TodoFragment.this::onTodoItemClicked);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void onTodoItemClicked(Todo todoItem, View sharedView) {
        Bundle data = new Bundle();
        data.putParcelable("todo_item", todoItem);
        //each shared view has a unique transition name, but the TodoDetailsFragment needs to know it.
        // That's why we need to pass that transition name to the fragment (and not only the Todo item)
        data.putString("transition_name", sharedView.getTransitionName());

        FragmentNavigator.Extras.Builder builder = new FragmentNavigator.Extras.Builder();

        //That's why we needed onTodoItemClicked to get us the actual shared view - because the
        // addSharedElement method needs it.
        builder.addSharedElement(sharedView, sharedView.getTransitionName());
        //This does not work, because the transition name is not unique and also, because the fragment
        //does not have a textView called txtTitle. The recycler view does.
//        builder.addSharedElement(getView().findViewById(R.id.txtTitle), "shared_element_view");
        FragmentNavigator.Extras extras = builder.build();

        Navigation.findNavController(getView()).navigate(R.id.action_todoFragment_to_todoDetailsFragment, data, null, extras);
    }

    private void openCreateTodoScreen(View view1) {
        Navigation.findNavController(view1).navigate(R.id.action_todoFragment_to_createTodoFragment);
    }
}