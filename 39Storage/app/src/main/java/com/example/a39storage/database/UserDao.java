package com.example.a39storage.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity userEntity);

    @Query("SELECT * FROM users")
    List<UserEntity> getAll();

    @Insert
    void insertAll(UserEntity... users);

    @Delete
    void delete(UserEntity user);
}

