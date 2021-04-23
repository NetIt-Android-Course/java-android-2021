package com.example.a43_background_work.di;

import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.data.remote.authentication.AuthenticationManager;
import com.example.a43_background_work.data.remote.dog_images.ApiWrapper;
import com.example.a43_background_work.presenters.RegisterPresenter;
import com.example.a43_background_work.presenters.contracts.register.RegisterPresenterListener;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public RegisterPresenterListener provideRegisterPresenterListener(ApiWrapper apiWrapper, AsyncDatabase asyncDatabase, AuthenticationManager authenticationManager) {
        return new RegisterPresenter(apiWrapper, asyncDatabase, authenticationManager);
    }
}
