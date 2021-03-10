package com.example.a42_networking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a42_networking.network.GenresResponseModel;
import com.example.a42_networking.network.QuotesApiService;
import com.example.a42_networking.network.QuotesResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {

    private QuotesApiService service;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edtName = view.findViewById(R.id.edtName);
        Button btnNext = view.findViewById(R.id.btnNext);
        Button btnQuote = view.findViewById(R.id.btnQuote);

        btnNext.setOnClickListener(view1 -> {
//            openDetailsScreen(view, edtName);
            getAllGenresFromApi();
        });

        btnQuote.setOnClickListener(view1 -> getAQuote());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quote-garden.herokuapp.com/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(QuotesApiService.class);
    }

    private void getAQuote() {
        service.getQuotes(1).enqueue(new Callback<QuotesResponseModel>() {
            @Override
            public void onResponse(Call<QuotesResponseModel> call, Response<QuotesResponseModel> response) {
                for (int i = 0; i < response.body().data.length; i++) {
                    Log.e("MainFragment", "Quote:" + response.body().data[i].quoteText + "  " +
                            response.body().data[i].quoteAuthor);
                }
            }

            @Override
            public void onFailure(Call<QuotesResponseModel> call, Throwable t) {

            }
        });
    }

    private void getAllGenresFromApi() {
        service.getAllGenres().enqueue(new Callback<GenresResponseModel>() {
            @Override
            public void onResponse(Call<GenresResponseModel> call, Response<GenresResponseModel> response) {
                if(response.isSuccessful()) {
                    GenresResponseModel responseModel = response.body();
                    for (int i = 0; i < responseModel.data.length; i++) {
                        Log.e("MainFragment", "Genre:" + responseModel.data[i]);
                    }

                } else {
                    Log.e("MainFragment", "Call failed");
                }
            }

            @Override
            public void onFailure(Call<GenresResponseModel> call, Throwable t) {
                Log.e("MainFragment", "Call failed", t);
            }
        });
    }

    private void openDetailsScreen(@NonNull View view, EditText edtName) {
        String name = edtName.getText().toString();
        Bundle fragmentData = new Bundle();
        fragmentData.putString("username", name);

        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailsFragment, fragmentData);
    }
}