package com.example.a43_background_work.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.a43_background_work.utils.ThreadUtils;
import com.example.a43_background_work.data.local.entities.UserEntity;
import com.example.a43_background_work.data.repositories.UserRepository;

import java.util.List;

public class AsyncDatabase implements UserRepository {

    private final UserDao userDao;

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

    @Override
    public LiveData<List<UserEntity>> getAllUsers() {
        return userDao.getAll();
    }

    public interface DataReceivedListener<T> {
        void onDataReceived(T data);
    }
}
