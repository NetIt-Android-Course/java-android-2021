package com.teo.a43_background_work.data.repositories;

import java.util.List;

public interface DogImageRepository {

    void getAllBreeds(OnApiResultListener<List<String>> callback);
    void getImagesUrlByBreed(String breed, OnApiResultListener<String[]> callback);

    public interface OnApiResultListener<T> {
        void onSuccess(T data);
        void onFailure();
    }
}
