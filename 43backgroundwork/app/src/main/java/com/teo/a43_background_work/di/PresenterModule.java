package com.teo.a43_background_work.di;

import com.teo.a43_background_work.data.repositories.AuthRepository;
import com.teo.a43_background_work.data.repositories.DogImageRepository;
import com.teo.a43_background_work.data.repositories.UserRepository;
import com.teo.a43_background_work.presenters.RegisterPresenter;
import com.teo.a43_background_work.presenters.contracts.register.RegisterPresenterListener;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public RegisterPresenterListener provideRegisterPresenterListener(DogImageRepository apiWrapper, UserRepository asyncDatabase, AuthRepository authenticationManager) {
        return new RegisterPresenter(apiWrapper, asyncDatabase, authenticationManager);
    }
}
