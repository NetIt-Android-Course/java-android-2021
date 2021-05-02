package com.teo.a43_background_work;

import android.app.Application;

import com.teo.a43_background_work.di.AppComponent;
import com.teo.a43_background_work.di.AppModule;
import com.teo.a43_background_work.di.DaggerAppComponent;
import com.teo.a43_background_work.di.DataModule;
import com.teo.a43_background_work.di.UserModule;

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
