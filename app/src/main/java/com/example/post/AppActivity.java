package com.example.post;

import android.app.Application;

import androidx.room.Room;

import com.example.post.Database.AppDB;

public class AppActivity extends Application {

    AppDB db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "posts.db").build();
    }
    }