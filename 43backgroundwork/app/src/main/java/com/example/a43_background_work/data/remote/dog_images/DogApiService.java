package com.example.a43_background_work.data.remote.dog_images;

import com.example.a43_background_work.data.remote.dog_images.models.BreedsResponse;
import com.example.a43_background_work.data.remote.dog_images.models.ImagesUrlsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApiService {

    @GET("breeds/list/all")
    Call<BreedsResponse> getAllBreeds();

    @GET("breed/{master_breed}/{sub_breed}/images")
    Call<ImagesUrlsResponse> getImagesUrlsBySubBreed(@Path("master_breed") String masterBreed,
                                                     @Path("sub_breed") String subBreed);

    @GET("breed/{master_breed}/images")
    Call<ImagesUrlsResponse> getImagesUrlsByBreed(@Path("master_breed") String masterBreed);
}
