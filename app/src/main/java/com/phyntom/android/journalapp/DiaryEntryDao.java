package com.phyntom.android.journalapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.phyntom.android.journalapp.DiaryEntry;

import java.util.List;

@Dao
public interface DiaryEntryDao {

    @Query("SELECT * FROM diary_entry order by entry_date,entry_time DESC")
    LiveData<List<DiaryEntry>> getAll();

    @Insert
    void insert(DiaryEntry entry);

    @Delete
    void delete(DiaryEntry entry);

    @Query("DELETE FROM diary_entry")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(DiaryEntry entry);



}
