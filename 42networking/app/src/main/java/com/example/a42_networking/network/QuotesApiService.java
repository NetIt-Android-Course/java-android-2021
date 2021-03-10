package com.example.a42_networking.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuotesApiService {

    //get quotes
    @GET("quotes")
    public Call<QuotesResponseModel> getQuotes(@Query("limit") int numberOfQuotes);

    //get all ganres
    @GET("genres")
    public Call<GenresResponseModel> getAllGenres();
}
