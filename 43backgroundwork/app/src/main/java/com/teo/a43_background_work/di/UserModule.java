package com.teo.a43_background_work.di;

import com.teo.a43_background_work.data.remote.authentication.AuthenticationManager;
import com.teo.a43_background_work.data.repositories.AuthRepository;
import com.teo.a43_background_work.data.repositories.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    public AuthRepository provideAuthenticationManager(UserRepository userRepository) {
        return new AuthenticationManager(userRepository);
    }

}
