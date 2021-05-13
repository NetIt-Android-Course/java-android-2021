package com.teo.movieapp.navigation_fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.teo.movieapp.MovieAdapter;
import com.teo.movieapp.R;
import com.teo.movieapp.constants.Crew;
import com.teo.movieapp.constants.Grade;
import com.teo.movieapp.constants.Review;
import com.teo.movieapp.models.Movie;
import java.util.Arrays;
import java.util.List;

public class StartFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView(view);

    }

    private void setUpRecyclerView(View view) {
        Movie movie1 = new Movie("Troy", 2004, 1, Review.TROY, Grade.VERY_GOOD,
                Crew.TROY, R.drawable.poster_troy);
        Movie movie2 = new Movie("Avatar", 2009, 2, Review.AVATAR, Grade.SUPERIOR,
                Crew.AVATAR, R.drawable.poster_avatar);
        Movie movie3 = new Movie("Taken", 2008, 3, Review.TAKEN, Grade.EXCELLENT,
                Crew.TAKEN, R.drawable.poster_taken);
        Movie movie4 = new Movie("Mortal Kombat", 2021, 4,  Review.MORTAL_KOMBAT,
                Grade.OUTSTANDING, Crew.MORTAL_KOMBAT, R.drawable.poster_mk);
        Movie movie5 = new Movie("Bad Boys", 1995, 5, Review.BAD_BOYS, Grade.GOOD,
                Crew.BAD_BOYS, R.drawable.poster_mk);
        Movie movie6 = new Movie("Godzilla vs Kong", 2021, 6, Review.GODZILA_VS_KONG,
                Grade.UNSATISFACTORY, Crew.GODZILLA_VS_KONG, R.drawable.poster_mk);
        Movie movie7 = new Movie("Zack Snyder's Justice League", 2021, 7,
                Review.ZACK_SNYDER, Grade.FAILURE, Crew.ZACK_SNYDER, R.drawable.poster_mk);
        Movie movie8 = new Movie("Rush Hour 3", 2007, 8, Review.RUSH_HOUR_3,
                Grade.SATISFACTORY, Crew.RUSH_HOUR_3, R.drawable.poster_mk);
        Movie movie9 = new Movie("Clash of the titans", 2010, 9, Review.CLASH_OF_TITANS,
                Grade.AVERAGE, Crew.CLASH_OF_TITANS, R.drawable.poster_mk);
        Movie movie10 = new Movie("Glitter", 2001, 10, Review.GLITTER,
                Grade.POOR, Crew.GLITTER, R.drawable.poster_mk);


        List<Movie> movies =  Arrays.asList(movie1, movie2, movie3, movie4, movie5,
                movie6, movie7, movie8, movie9, movie10);
        recyclerView = view.findViewById(R.id.recMovieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter adapter = new MovieAdapter(movies);
        recyclerView.setAdapter(adapter);
    }
}
