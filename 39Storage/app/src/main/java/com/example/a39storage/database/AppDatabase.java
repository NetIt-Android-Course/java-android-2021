package com.example.a39storage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) instance =
                Room.databaseBuilder(context, AppDatabase.class, "mydb-db")
                        .fallbackToDestructiveMigration()
                        .build();
        return instance;
    }

    public abstract UserDao userDao();
}
