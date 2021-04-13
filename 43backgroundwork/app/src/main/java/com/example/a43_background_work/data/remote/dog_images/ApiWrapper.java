package com.example.a43_background_work.data.remote.dog_images;

import com.example.a43_background_work.JsonUtils;
import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.remote.dog_images.models.BreedsResponse;
import com.example.a43_background_work.data.remote.dog_images.models.ImagesUrlsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWrapper {

    private static ApiWrapper instance;
    private final DogApiService service;

    public static void init(AsyncDatabase asyncDatabase) {
        if(instance == null) instance = new ApiWrapper(asyncDatabase);
    }

    public static ApiWrapper getInstance() {
        return instance;
    }

    private ApiWrapper(AsyncDatabase asyncDatabase) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://cat.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        service = retrofit.create(DogApiService.class);
    }

    public void getAllBreeds(OnApiResultListener<List<String>> callback) {
        service.getAllBreeds().enqueue(new Callback<BreedsResponse>() {
            @Override
            public void onResponse(Call<BreedsResponse> call, Response<BreedsResponse> response) {
                if(response.isSuccessful()) {
                    List<String> breedsFromJson = JsonUtils.getBreedsFromJson(response.body().getMessage());
                    callback.onSuccess(breedsFromJson);
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<BreedsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void getImagesUrlByBreed(String breed, OnApiResultListener<String[]> callback) {
        String[] breedArr = breed.split(" ");
        if(breedArr.length == 1) {
            getImagesUrlByMasterBreed(breed, callback);
        } else {
            getImagesUrlBySubbreed(breedArr[1], breedArr[0], callback);
        }
    }

    private void getImagesUrlBySubbreed(String masterBreed, String subBreed, OnApiResultListener<String[]> callback) {
        service.getImagesUrlsBySubBreed(masterBreed, subBreed).enqueue(new Callback<ImagesUrlsResponse>() {
            @Override
            public void onResponse(Call<ImagesUrlsResponse> call, Response<ImagesUrlsResponse> response) {
                if(response.isSuccessful()) {
                    String[] imageUrls = response.body().getMessage();
                    callback.onSuccess(imageUrls);
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ImagesUrlsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    private void getImagesUrlByMasterBreed(String breed, OnApiResultListener<String[]> callback) {
        service.getImagesUrlsByBreed(breed).enqueue(new Callback<ImagesUrlsResponse>() {
            @Override
            public void onResponse(Call<ImagesUrlsResponse> call, Response<ImagesUrlsResponse> response) {
                if(response.isSuccessful()) {
                    String[] imageUrls = response.body().getMessage();
                    callback.onSuccess(imageUrls);
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ImagesUrlsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public interface OnApiResultListener<T> {
        void onSuccess(T data);
        void onFailure();
    }
}
