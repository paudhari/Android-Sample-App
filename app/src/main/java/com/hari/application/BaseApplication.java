package com.hari.application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.hari.database.AppDatabase;
import com.hari.utils.Constants;

public class BaseApplication extends Application {

    public static Context mContext = null;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        getAppDatabase(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, Constants.DB_NAME).
                    allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
}
