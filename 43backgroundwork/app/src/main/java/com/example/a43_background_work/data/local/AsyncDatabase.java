package com.example.a43_background_work.data.local;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.a43_background_work.ThreadUtils;
import com.example.a43_background_work.data.local.entities.UserEntity;

import java.util.List;

public class AsyncDatabase {

    private static AsyncDatabase instance;
    private final UserDao userDao;

    public static void init(Context context) {
        if(instance == null) {
            instance = new AsyncDatabase(context);
        }
    }

    public static AsyncDatabase getInstance() {
        if(instance == null) {
            throw new NullPointerException("AsyncDatabase is not initiliazized.");
        }
        return instance;
    }

    public AsyncDatabase(Context context) {
        userDao = AppDatabase.getInstance(context).userDao();
    }

    public void insert(final UserEntity userEntity) {
        ThreadUtils.executorService().submit(() -> userDao.insert(userEntity));
    }

    public void getUserByNameAndAge(String name, int age, DataReceivedListener<UserEntity> callback) {
        ThreadUtils.executorService().submit(() -> {
            UserEntity userEntity = userDao.findByName(name, age);
            ThreadUtils.mainThread().post(() -> callback.onDataReceived(userEntity));
        });
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return userDao.getAll();
    }

    public interface DataReceivedListener<T> {
        void onDataReceived(T data);
    }
}
