package com.example.a43_background_work.di;

import com.example.a43_background_work.ui.MainActivity;
import com.example.a43_background_work.ui.fragments.MainFragment;
import com.example.a43_background_work.ui.fragments.RegisterFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UserModule.class, DataModule.class, AppModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
    void inject(RegisterFragment fragment);
}
