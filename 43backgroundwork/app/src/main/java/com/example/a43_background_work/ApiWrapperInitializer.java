package com.example.a43_background_work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.remote.dog_images.ApiWrapper;

import java.util.Arrays;
import java.util.List;

public class ApiWrapperInitializer implements Initializer<ApiWrapper> {


    @NonNull
    @Override
    public ApiWrapper create(@NonNull Context context) {
        ApiWrapper.init(AsyncDatabase.getInstance());
        return ApiWrapper.getInstance();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Arrays.asList(AsyncDatabaseInitializer.class);
    }
}
