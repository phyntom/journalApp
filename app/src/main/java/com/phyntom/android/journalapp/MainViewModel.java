package com.phyntom.android.journalapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<DiaryEntry>> diaryEntryLists;

    private AppDatabase appDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        diaryEntryLists = appDatabase.diaryEntryDao().getAll();
    }

    /**
     * method to retrieve all the diary entries
     *
     * @return list of all entries
     */
    public LiveData<List<DiaryEntry>> getDiaryEntryLists() {
        return diaryEntryLists;
    }

    /**
     * method to delete diaryEntry
     *
     * @param diaryEntry
     */
    public void deleteEntry(DiaryEntry diaryEntry) {
        new AsyncTask<DiaryEntry, Void, Void>() {
            @Override
            protected Void doInBackground(DiaryEntry... params) {
                DiaryEntry entry = params[0];
                appDatabase.diaryEntryDao().delete(entry);
                return null;
            }
        }.execute(diaryEntry);
    }

    /**
     * method to insert diaryEntry
     *
     * @param diaryEntry
     */
    public void insertEntry(DiaryEntry diaryEntry) {
        new AsyncTask<DiaryEntry, Void, Void>() {
            @Override
            protected Void doInBackground(DiaryEntry... params) {
                DiaryEntry entry = params[0];
                appDatabase.diaryEntryDao().insert(entry);
                return null;
            }
        }.execute(diaryEntry);
    }


    /**
     * method to insert diaryEntry
     *
     * @param diaryEntry
     */
    public void updateEntry(DiaryEntry diaryEntry) {
        new AsyncTask<DiaryEntry, Void, Void>() {
            @Override
            protected Void doInBackground(DiaryEntry... params) {
                DiaryEntry entry = params[0];
                appDatabase.diaryEntryDao().update(entry);
                return null;
            }
        }.execute(diaryEntry);
    }

}
