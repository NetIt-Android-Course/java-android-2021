package com.example.a43_background_work.data.remote.dog_images;

import com.example.a43_background_work.utils.JsonUtils;
import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.remote.dog_images.models.BreedsResponse;
import com.example.a43_background_work.data.remote.dog_images.models.ImagesUrlsResponse;
import com.example.a43_background_work.data.repositories.DogImageRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiWrapper implements DogImageRepository {

    private final DogApiService service;

    public ApiWrapper(AsyncDatabase asyncDatabase, DogApiService service) {
        this. service = service;
    }

    public void getAllBreeds(DogImageRepository.OnApiResultListener<List<String>> callback) {
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

    public void getImagesUrlByBreed(String breed, DogImageRepository.OnApiResultListener<String[]> callback) {
        String[] breedArr = breed.split(" ");
        if(breedArr.length == 1) {
            getImagesUrlByMasterBreed(breed, callback);
        } else {
            getImagesUrlBySubbreed(breedArr[1], breedArr[0], callback);
        }
    }

    private void getImagesUrlBySubbreed(String masterBreed, String subBreed, DogImageRepository.OnApiResultListener<String[]> callback) {
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

    private void getImagesUrlByMasterBreed(String breed, DogImageRepository.OnApiResultListener<String[]> callback) {
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
}
