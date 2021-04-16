package com.example.a43_background_work.di;

import com.example.a43_background_work.ui.MainActivity;
import com.example.a43_background_work.ui.fragments.MainFragment;
import com.example.a43_background_work.ui.fragments.RegisterFragment;

import dagger.Component;

@Component(modules = {UserModule.class, DataModule.class, AppModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
    void inject(RegisterFragment fragment);
}
