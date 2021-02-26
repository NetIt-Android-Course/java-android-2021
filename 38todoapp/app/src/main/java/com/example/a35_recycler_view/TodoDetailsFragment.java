package com.example.a35_recycler_view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a35_recycler_view.data.Todo;
import com.google.android.material.transition.MaterialContainerTransform;

public class TodoDetailsFragment extends Fragment {

    private Todo todoItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(new MaterialContainerTransform());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        todoItem = getArguments().getParcelable("todo_item");

        //We now can get the transition name that was passed to this fragment.
        String sharedElementTransitionName = getArguments().getString("transition_name");

        View view =  inflater.inflate(R.layout.fragment_todo_details, container, false);

        //We don't have a transition name set in the xml anymore, that's why we need to set it programatically.
        view.findViewById(R.id.txtTitle).setTransitionName(sharedElementTransitionName);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), todoItem.toString(), Toast.LENGTH_SHORT).show();

        ((TextView) view.findViewById(R.id.txtTitle)).setText(todoItem.getTitle());
        ((TextView) view.findViewById(R.id.txtDetails)).setText(todoItem.getText());
        ((TextView) view.findViewById(R.id.txtDueDate)).setText(getFormattedDate(todoItem.getDueDate()));
        ((TextView) view.findViewById(R.id.txtPriority)).setText(getFormattedPriority(todoItem.getPriority()));
        view.findViewById(R.id.viewDifficulty).setBackgroundColor(
                getContext().getColor(ConstantToResConverter.getDifficultyColor(todoItem))
        );
    }

    private String getFormattedPriority(int priority) {
        return "Priority: HIGH";
    }

    private String getFormattedDate(long dueDate) {
        return "22 Feb 2021";
    }
}