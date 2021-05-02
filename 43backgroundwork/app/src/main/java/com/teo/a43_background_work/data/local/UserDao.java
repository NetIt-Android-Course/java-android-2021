package com.teo.a43_background_work.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.teo.a43_background_work.data.local.entities.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAll();

    @Query("SELECT * FROM users WHERE name LIKE :userName AND " +
            "age LIKE :userAge LIMIT 1")
    UserEntity findByName(String userName, int userAge);

    @Insert
    void insert(UserEntity user);

    @Delete
    void delete(UserEntity user);
}