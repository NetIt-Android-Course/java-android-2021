package com.teo.a43_background_work.di;

import android.content.Context;

import com.teo.a43_background_work.data.local.AsyncDatabase;
import com.teo.a43_background_work.data.remote.dog_images.ApiWrapper;
import com.teo.a43_background_work.data.remote.dog_images.DogApiService;
import com.teo.a43_background_work.data.repositories.DogImageRepository;
import com.teo.a43_background_work.data.repositories.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    public DogImageRepository providesApiWrapper(DogApiService service) {
        return new ApiWrapper(service);
    }

    @Provides
    public UserRepository providesAsyncdatabase(Context context) {
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
