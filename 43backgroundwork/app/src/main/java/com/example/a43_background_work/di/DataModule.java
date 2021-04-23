package com.example.a43_background_work.di;

import android.content.Context;

import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.remote.dog_images.ApiWrapper;
import com.example.a43_background_work.data.remote.dog_images.DogApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    public ApiWrapper providesApiWrapper(AsyncDatabase asyncDatabase, DogApiService service) {
        return new ApiWrapper(asyncDatabase, service);
    }

    @Provides
    public AsyncDatabase providesAsyncdatabase(Context context) {
        return new AsyncDatabase(context);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Singleton
    @Provides
    public DogApiService providesDogService(Retrofit retrofit) {
        return retrofit.create(DogApiService.class);
    }
}
