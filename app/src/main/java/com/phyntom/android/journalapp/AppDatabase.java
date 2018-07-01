package com.phyntom.android.journalapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {DiaryEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();

    private static final String DB_NAME = "journal_db";

    private static AppDatabase INSTANCE;

    /**
     * method to create database by using singleton pattern to prevent having multiple
     * instances of the database opened at the same time
     *
     * @param context
     * @return
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            .build();
                }
            }
            Log.i(TAG, "Getting the database instance");
        }
        return INSTANCE;
    }

    public abstract DiaryEntryDao diaryEntryDao();

}
