package com.example.a43_background_work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.example.a43_background_work.data.local.AsyncDatabase;

import java.util.Collections;
import java.util.List;

public class AsyncDatabaseInitializer implements Initializer<AsyncDatabase> {

    @NonNull
    @Override
    public AsyncDatabase create(@NonNull Context context) {
        AsyncDatabase.init(context);
        return AsyncDatabase.getInstance();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
