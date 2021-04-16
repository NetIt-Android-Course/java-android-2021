package com.example.a43_background_work.di;

import com.example.a43_background_work.data.remote.authentication.AuthenticationManager;
import com.example.a43_background_work.data.remote.dog_images.ApiWrapper;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    public AuthenticationManager provideAuthenticationManager() {
        return new AuthenticationManager();
    }

}
