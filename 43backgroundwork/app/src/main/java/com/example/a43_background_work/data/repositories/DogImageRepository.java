package com.example.a43_background_work.data.repositories;

import com.example.a43_background_work.data.remote.dog_images.ApiWrapper;

import java.util.List;

public interface DogImageRepository {

    void getAllBreeds(OnApiResultListener<List<String>> callback);
    void getImagesUrlByBreed(String breed, OnApiResultListener<String[]> callback);

    public interface OnApiResultListener<T> {
        void onSuccess(T data);
        void onFailure();
    }
}
