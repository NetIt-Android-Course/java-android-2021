package com.teo.a43_background_work.data.repositories;

import androidx.lifecycle.LiveData;

import com.teo.a43_background_work.data.local.entities.UserEntity;

import java.util.List;

public interface UserRepository {

    LiveData<List<UserEntity>> getAllUsers();

    void insert(String name, int age, String email);
}
