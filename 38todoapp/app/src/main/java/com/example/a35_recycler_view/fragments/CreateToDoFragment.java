package com.example.a35_recycler_view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.a35_recycler_view.R;
import com.example.a35_recycler_view.data.Repository;
import com.example.a35_recycler_view.data.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateToDoFragment extends Fragment {

    private EditText edtTitle;
    private EditText edtText;
    private EditText edtDate;
    private Spinner spnPriority;
    private SeekBar seekDifficulty;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_to_do, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar(view);
        setupViews(view);
        view.findViewById(R.id.btnCreate).setOnClickListener(view1 -> onCreateTodoClicked());
    }

    private void setupViews(View view) {
        edtTitle = view.findViewById(R.id.edtTitle);
        edtText = view.findViewById(R.id.edtText);
        edtDate = view.findViewById(R.id.edtDate);
        spnPriority = view.findViewById(R.id.spnPriority);
        seekDifficulty = view.findViewById(R.id.seekDifficulty);

        spnPriority.setAdapter(
                new ArrayAdapter<String>(
                        getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.priorities)));
    }

    private void onCreateTodoClicked() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        long dateTimestamp = 0L;
        try {
            Date date = sdf.parse(edtDate.getText().toString());
            dateTimestamp = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Todo todo = new Todo(
                edtTitle.getText().toString(),
                edtText.getText().toString(),
                spnPriority.getSelectedItemPosition() + 1,
                seekDifficulty.getProgress() + 1,
                dateTimestamp
        );
        Repository.getInstance().addTodo(todo);
        navController.navigateUp();
    }

    private void setupToolbar(@NonNull View view) {
        navController = Navigation.findNavController(getActivity(), R.id.containerView);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }
}