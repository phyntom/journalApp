package com.phyntom.android.journalapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    List<DiaryEntry> dataList = new ArrayList<>();

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private DiaryEntryAdapter entryAdapter;

    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);


        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // initializing the adapter
        entryAdapter = new DiaryEntryAdapter(this);
        entryAdapter.setDiaryEntryList(dataList);

        // specifying the adapter to access the data and create views and replace the content
        recyclerView.setAdapter(entryAdapter);
        // initialize the appDatabase object
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        // retrieve database entries from the local database
        updateViewModel();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * method to retrieve diary entries after update
     */
    private void updateViewModel() {
        Log.d(TAG, "Loading data after refresh...");
        MainViewModel viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getDiaryEntryLists().observe(this, new Observer<List<DiaryEntry>>() {
            @Override
            public void onChanged(@Nullable List<DiaryEntry> diaryEntries) {
                Log.d(TAG, "Updating list of entries from liveData to viewModel");
                entryAdapter.setDiaryEntryList(diaryEntries);
                entryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void addDiaryEntry(View view) {
        Intent startDiaryEntryActivity = new Intent(this, DiaryEntryActivity.class);
        startActivity(startDiaryEntryActivity);
    }
}
