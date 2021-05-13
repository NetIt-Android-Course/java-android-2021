package com.teo.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.teo.movieapp.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtTitle;
    private final TextView txtGrade;
    private List<Movie> data;
    private final Bundle bundle;



    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtGrade = itemView.findViewById(R.id.txtGrade);
        bundle = new Bundle();
    }

    @SuppressLint("SetTextI18n")
    public void createSingleRow(Movie movie, int position) {

        txtTitle.setText(movie.getName());
        txtGrade.setText(movie.getGrade().toString() + "/10");


        itemView.setOnClickListener(v -> {
            navigateTo(position);
        });
    }


    private void navigateTo(int position) {

        bundle.putParcelable("selectedMovie", data.get(position));

        Navigation.findNavController(itemView).navigate(R.id.action_startFragment_to_movieFragment, bundle);
    }


    public void transferData(List<Movie> data) {
        this.data = data;
    }
}
