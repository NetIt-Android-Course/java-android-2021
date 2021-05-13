package com.teo.movieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teo.movieapp.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<Movie> data;

    public MovieAdapter(List<Movie> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleElement =
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(singleElement);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            Movie movie = data.get(position);
            holder.createSingleRow(movie, position);
            holder.transferData(data);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
