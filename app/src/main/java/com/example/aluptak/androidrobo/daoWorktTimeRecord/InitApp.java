package com.example.aluptak.androidrobo.daoWorktTimeRecord;

import android.app.Application;

import com.example.aluptak.androidrobo.daoWorktTimeRecord.DbHelper;

/**
 * Created by aluptak on 02/02/2016.
 */
public class InitApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        //initial database creation..
        new DbHelper(getApplicationContext()).getWritableDatabase();

    }
}