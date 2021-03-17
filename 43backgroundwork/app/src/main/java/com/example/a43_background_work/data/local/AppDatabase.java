package com.example.a43_background_work.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a43_background_work.data.local.entities.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "dog-database.db").build();
        }
        return instance;
    }


    public abstract UserDao userDao();

}
