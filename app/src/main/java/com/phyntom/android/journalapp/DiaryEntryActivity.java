package com.phyntom.android.journalapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

public class DiaryEntryActivity extends AppCompatActivity {

    TextView textViewTime;

    EditText editTextEntryDesc;

    private AppDatabase appDatabase;

    private static final String TAG=DiaryEntryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);
        textViewTime=(TextView)findViewById(R.id.tv_time);
        editTextEntryDesc=(EditText)findViewById(R.id.et_entry_desc) ;

        textViewTime.setText(LocalTime.now().toString());
        appDatabase=AppDatabase.getDatabase(getApplicationContext());
    }

    /**
     * method used to save diary entry to database
     * @param view
     */
    public void onSaveButtonClicked(View view){
        if(TextUtils.isEmpty(editTextEntryDesc.getText())){
            Toast.makeText(this,"Cannot save empty diary entry",Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            try {
                LocalDate currentDate = LocalDate.now();
                String time = textViewTime.getText().toString();
                String entryDesc = editTextEntryDesc.getText().toString();
                DiaryEntry diaryEntry = new DiaryEntry(currentDate, time, entryDesc);
                MainViewModel viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
                viewModel.insertEntry(diaryEntry);
                finish();
            }
            catch (Exception ex){
                Log.e(TAG,"Diary entry not save "+ex);
            }
        }
    }
}
