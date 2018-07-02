package com.phyntom.android.journalapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DiaryEntryDaoTest {

    private DiaryEntryDao mDiaryEntryDao;

    private AppDatabase mDatabase;

    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase= Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                .build();
        mDiaryEntryDao= mDatabase.diaryEntryDao();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }



}
