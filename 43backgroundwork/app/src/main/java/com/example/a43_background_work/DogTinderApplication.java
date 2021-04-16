package com.example.a43_background_work;

import android.app.Application;

import com.example.a43_background_work.data.local.AsyncDatabase;
import com.example.a43_background_work.di.AppComponent;
import com.example.a43_background_work.di.AppModule;
import com.example.a43_background_work.di.DaggerAppComponent;
import com.example.a43_background_work.di.DataModule;
import com.example.a43_background_work.di.UserModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DogTinderApplication extends Application {


    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .userModule(new UserModule())
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
